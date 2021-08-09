package boot.spring.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boot.spring.controller.pojo.FormValue1;
import boot.spring.controller.pojo.OrderMsg;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FormValue;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import boot.spring.pagemodel.Process;
import boot.spring.mapper.LeaveApplyMapper;
import boot.spring.pagemodel.DataGrid;
import boot.spring.pagemodel.HistoryProcess;
import boot.spring.pagemodel.LeaveTask;
import boot.spring.pagemodel.MSG;
import boot.spring.po.LeaveApply;
import boot.spring.po.Permission;
import boot.spring.po.Role;
import boot.spring.po.Role_permission;
import boot.spring.po.User;
import boot.spring.po.User_role;
import boot.spring.service.LeaveService;
import boot.spring.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "请假流程接口")
@Controller
public class ActivitiController {
    @Autowired
    RepositoryService rep;

    @Autowired
    RuntimeService runservice;

    @Autowired
    FormService formservice;

    @Autowired
    IdentityService identityservice;

    @Autowired
    LeaveService leaveservice;

    @Autowired
    TaskService taskservice;

    @Autowired
    HistoryService histiryservice;

    @Autowired
    SystemService systemservice;

    @Autowired
    LeaveApplyMapper leaveApplyMapper;

    public static void main(String[] args) throws Exception {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		//Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection connectionLocal= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false","root","root");
//		Statement statement = connectionLocal.createStatement();
//
//		statement.addBatch("insert into gaizhang (wid,owner,create_time,status,company,`type`,spr,reason) values (1,1,'2020-1-1',1,1,1,1,1)");
//		statement.executeBatch();
//
//		statement.close();
//		connectionLocal.close();
        ex();
    }


    //	@GetMapping("ex")
    public static void ex() {

        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://172.16.0.242:3306/db_work_flow", "guoshiying", "iG8CeqlNF6ukhx4l");


            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connectionLocal = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false", "root", "root");

//			Statement statement = connectionLocal.createStatement();

//
//            //变量
//            String sql = "SELECT work_order_id,form_value  FROM t_work_flow_variable    where task_type='startEvent' and DATE(create_time)>='2020-06-01' and DATE(create_time)<='2021-05-31'\n" +
//                    "\n" +
//                    "and work_order_id in (\n" +
//                    "\t\t\tSELECT id\n" +
//                    "                     \n" +
//                    "                     \n" +
//                    "                       FROM t_work_order where id in ( \n" +
//                    "                    select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31' \n" +
//                    "                    ) and `status` in (2,3) and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
//                    "\n" +
//                    ")";

            //变量xx
            String sql = "SELECT work_order_id,form_value  FROM t_work_flow_variable    where task_type='startEvent' and DATE(create_time)>='2020-06-01' and DATE(create_time)<='2021-05-31'\n" +
                    "\n" +
                    "and work_order_id in (\n" +
                    "\t\t\tSELECT id\n" +
                    "                     \n" +
                    "                     \n" +
                    "                       FROM t_work_order where id in ( \n" +
                    "                    select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and status=-1 and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31' \n" +
                    "                    ) and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
                    "\n" +
                    ")";


//            String sqlP = "select work_order_id,processor_name from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n";
            String sqlP = "select work_order_id,processor_name from t_work_order_processor  WHERE processor_group_name='印章管理专员'  and status=-1  and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n";


            //办理人
            Map<String, String> order = new HashMap<>();

            PreparedStatement pstmtp = connection.prepareStatement(sqlP);
            ResultSet rsp = pstmtp.executeQuery();
            while (rsp.next()) {
                String id = rsp.getString("work_order_id");
                String processor_name = rsp.getString("processor_name");
                order.put(id, processor_name);
//                System.out.println(id+"   "+processor_name);
            }

            rsp.close();
            pstmtp.close();


//            String sql3 = "\n" +
//                    "SELECT id,owner_name,create_time,\n" +
//                    "\n" +
//                    "case \n" +
//                    "`status`\n" +
//                    "WHEN 2 THEN '已解决待确认'\n" +
//                    "WHEN 3 THEN '已解决'\n" +
//                    "end `status` \n" +
//                    "\n" +
//                    "\n" +
//                    "   FROM t_work_order where id in (\n" +
//                    "select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
//                    ") and `status` in (2,3) and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'";


            String sql3 = "\n" +
                    "SELECT id,owner_name,create_time,\n" +
                    "`status` \n" +
                    "\n" +
                    "\n" +
                    "   FROM t_work_order where id in (\n" +
                    "select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员'  and status=-1  and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
                    ") and  (create_time)>='2020-06-01' and (create_time)<='2021-05-31'";

            //工单信息
            Map<String, OrderMsg> msg = new HashMap<>();

            PreparedStatement pstmtM = connection.prepareStatement(sql3);
            ResultSet rsM = pstmtM.executeQuery();
            while (rsM.next()) {

                String id = rsM.getString("id");
                String ownerName = rsM.getString("owner_name");
                String createTime = rsM.getString("create_time");
                String status = rsM.getString("status");

                msg.put(id, new OrderMsg(id, ownerName, createTime, status));
            }

            rsM.close();
            pstmtM.close();

            ArrayList<Map> list = new ArrayList<>();


            Map<String, String> var = new HashMap<>();

            HashMap<String, FormValue1> formValueHashMap = new HashMap<String, FormValue1>();


            //变量
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                String id = rs.getString("work_order_id");

                if (order.get(id) != null) {


                    String form_value = rs.getString("form_value");

                    OrderMsg orderMsg = msg.get(id);
                    if (orderMsg != null) {

                        orderMsg.setJson(form_value);
                    }


                    var.put(id, form_value);

                    JSONArray objects = JSONObject.parseArray(form_value);


                    String yinZhangCompany = null;
                    String reason = null;
                    String yinZhangType = null;

                    for (Object object : objects) {

                        Map<String, Object> map = (Map) object;
//						Object o = map.get("elementLabel");
//						Object o1 = map.get("userValue");
//						if (o instanceof  String&& o1 instanceof  String){
                        String type = (String) map.get("elementLabel").toString();
                        String userValue = (String) map.get("userValue").toString();
                        if (type != null && (type).contains("文件所属公司")) {
                            yinZhangCompany = userValue;
                        }

                        if (type != null && (type).equals("申请事由")) {
                            reason = userValue;
                        }


                        if (type != null && (type).equals("印章类型")) {
                            yinZhangType = userValue;
                        }

//						}


                    }
//					System.out.println(id+"  "+yinZhangCompany+"  "+reason+"   "+yinZhangType);
                    formValueHashMap.put(id, new FormValue1(id, yinZhangCompany, reason, yinZhangType));

                }


            }

            rs.close();
            pstmt.close();


            for (Object k : ((HashMap) msg).keySet()) {

                String key = (String) k;
                if (order.get(key) != null && formValueHashMap.get(key) != null) {

                    HashMap<String, Object> map = new HashMap<>();
                    OrderMsg orderMsg = msg.get(key);
                    orderMsg.setCompanyYz(formValueHashMap.get(key).getYinZhangCompany());
                    orderMsg.setTypeYz(formValueHashMap.get(key).getYinZhangType());
                    orderMsg.setReason(formValueHashMap.get(key).getReason());
                    orderMsg.setSpr(order.get(key));
                    System.out.println(orderMsg);

                    map.put("工单号", orderMsg.getId());
                    map.put("发起人", orderMsg.getOwnerName());
                    map.put("创建时间", orderMsg.getCreateTime());
                    map.put("状态", orderMsg.getStatus());
                    map.put("印章所属公司", orderMsg.getCompanyYz());
                    map.put("印章类型", orderMsg.getTypeYz());
                    map.put("审批人", orderMsg.getSpr());
                    map.put("原因", orderMsg.getReason());


                    String sqlLocal = "insert into gaizhang (wid,owner,create_time,status,company,`type`,spr,reason" +
                            "," +
                            "json" +
                            ") values (" +
                            "?," +
                            "?," +
                            "?," +
                            "?," +
                            "?," +
                            "?," +
                            "?," +
                            "?," +
                            "?" +
                            ")";
//					String sqlLocal="insert into gaizhang (wid,owner,create_time,status,company,`type`,spr,reason,json) " +
//							"values ('"+orderMsg.getId()+"','"+orderMsg.getOwnerName()+"','"+orderMsg.getCreateTime()+"','"+orderMsg.getStatus()+"','"+orderMsg.getCompanyYz()+"','"+orderMsg.getTypeYz()
//							+"','"+orderMsg.getSpr()
//							+"','"+orderMsg.getReason()+"')";

//					statement.addBatch(sqlLocal	);

                    PreparedStatement preparedStatement = connectionLocal.prepareStatement(sqlLocal);


                    preparedStatement.setString(1, orderMsg.getId());
                    preparedStatement.setString(2, orderMsg.getOwnerName());
                    preparedStatement.setString(3, orderMsg.getCreateTime());
                    preparedStatement.setString(4, orderMsg.getStatus());
                    preparedStatement.setString(5, orderMsg.getCompanyYz());
                    preparedStatement.setString(6, orderMsg.getTypeYz());
                    preparedStatement.setString(7, orderMsg.getSpr());
                    preparedStatement.setString(8, orderMsg.getReason());
                    preparedStatement.setString(9, orderMsg.getJson());

//					prestmt.setString(1,sno);

                    preparedStatement.execute();
//
                    preparedStatement.close();
//					list.add(map);

                }
            }


            connectionLocal.close();

//			OutputStream outputStream=new FileOutputStream("D:\\1.xlsx");
//
//
//			//定义Excel正文背景颜色
//			TableStyle tableStyle=new TableStyle();
//			tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
//
//			//定义Excel正文字体大小
//			Font font=new Font();
//			font.setFontHeightInPoints((short) 10);
//
//			tableStyle.setTableContentFont(font);
//
//			Table table=new Table(0);
//			table.setTableStyle(tableStyle);
//
////            EasyExcelUtil.writeExcelWithModel(outputStream,list,table,OrderMsg.class,ExcelTypeEnum.XLSX);
////			ExcelUtil.exportMultisheetExcel("a",list,response);
//
//
////JSONArray.toJSONString(list);
//
//
//			String[] headers = {"工单号", "发起人", "创建时间", "印章所属公司", "印章类型","审批人","原因"};
//			String fileName = "学生表";
//
//			Map<String, Object> studentMap = new HashMap();
//			studentMap.put("headers", headers);
//			studentMap.put("dataList", list);
//			studentMap.put("fileName", fileName);
//
//			List<Map> mapList = new ArrayList();
//			mapList.add(studentMap);
//			ExcelUtil.exportMultisheetExcel(fileName, mapList, response);


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		}
//
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/processlist", method = RequestMethod.GET)
    String process() {
        return "activiti/processlist";
    }

    @ApiOperation("上传一个工作流文件")
    @RequestMapping(value = "/uploadworkflow", method = RequestMethod.POST)
    public String fileupload(@RequestParam MultipartFile uploadfile, HttpServletRequest request) {
        try {
            MultipartFile file = uploadfile;
            String filename = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            rep.createDeployment().addInputStream(filename, is).deploy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @ApiOperation("查询已部署工作流列表")
    @RequestMapping(value = "/getprocesslists", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<Process> getlist(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount) {
        int firstrow = (current - 1) * rowCount;
        List<ProcessDefinition> list = rep.createProcessDefinitionQuery().listPage(firstrow, rowCount);
        int total = rep.createProcessDefinitionQuery().list().size();
        List<Process> mylist = new ArrayList<Process>();
        for (int i = 0; i < list.size(); i++) {
            Process p = new Process();
            p.setDeploymentId(list.get(i).getDeploymentId());
            p.setId(list.get(i).getId());
            p.setKey(list.get(i).getKey());
            p.setName(list.get(i).getName());
            p.setResourceName(list.get(i).getResourceName());
            p.setDiagramresourcename(list.get(i).getDiagramResourceName());
            mylist.add(p);
        }
        DataGrid<Process> grid = new DataGrid<Process>();
        grid.setCurrent(current);
        grid.setRowCount(rowCount);
        grid.setRows(mylist);
        grid.setTotal(total);
        return grid;
    }

    @ApiOperation("查看工作流图片")
    @RequestMapping(value = "/showresource", method = RequestMethod.GET)
    public void export(@RequestParam("pdid") String pdid, @RequestParam("resource") String resource,
                       HttpServletResponse response) throws Exception {
        ProcessDefinition def = rep.createProcessDefinitionQuery().processDefinitionId(pdid).singleResult();
        InputStream is = rep.getResourceAsStream(def.getDeploymentId(), resource);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is, output);
    }

    @RequestMapping(value = "/deletedeploy", method = RequestMethod.POST)
    public String deletedeploy(@RequestParam("deployid") String deployid) throws Exception {
        rep.deleteDeployment(deployid, true);
        return "activiti/processlist";
    }

    @RequestMapping(value = "/runningprocess", method = RequestMethod.GET)
    public String task() {
        return "activiti/runningprocess";
    }

    @RequestMapping(value = "/deptleaderaudit", method = RequestMethod.GET)
    public String mytask() {
        return "activiti/deptleaderaudit";
    }

    @RequestMapping(value = "/hraudit", method = RequestMethod.GET)
    public String hr() {
        return "activiti/hraudit";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String my() {
        return "index";
    }

    @RequestMapping(value = "/leaveapply", method = RequestMethod.GET)
    public String leave() {
        return "activiti/leaveapply";
    }

    @RequestMapping(value = "/reportback", method = RequestMethod.GET)
    public String reprotback() {
        return "activiti/reportback";
    }

    @RequestMapping(value = "/modifyapply", method = RequestMethod.GET)
    public String modifyapply() {
        return "activiti/modifyapply";
    }

    @ApiOperation("发起一个请假流程")
    @RequestMapping(value = "/startleave", method = RequestMethod.POST)
    @ResponseBody
    public MSG start_leave(LeaveApply apply, HttpSession session) {
        String userid = (String) session.getAttribute("username");
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyuserid", userid);
        variables.put("deptleader", apply.getDeptleader());
        leaveservice.startWorkflow(apply, userid, variables);
        return new MSG("sucess");
    }

    @ApiOperation("获取部门领导审批待办列表")
    @RequestMapping(value = "/depttasklist", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<LeaveTask> getdepttasklist(HttpSession session, @RequestParam("current") int current,
                                               @RequestParam("rowCount") int rowCount) {
        String username = (String) session.getAttribute("username");
        DataGrid<LeaveTask> grid = new DataGrid<LeaveTask>();
        grid.setRowCount(rowCount);
        grid.setCurrent(current);
        int firstrow = (current - 1) * rowCount;
        List<LeaveApply> results = leaveservice.getpagedepttask(username, firstrow, rowCount);
        int totalsize = leaveservice.getalldepttask(username);
        List<LeaveTask> tasks = new ArrayList<LeaveTask>();
        for (LeaveApply apply : results) {
            LeaveTask task = new LeaveTask();
            task.setApply_time(apply.getApply_time());
            task.setUser_id(apply.getUser_id());
            task.setEnd_time(apply.getEnd_time());
            task.setId(apply.getId());
            task.setLeave_type(apply.getLeave_type());
            task.setProcess_instance_id(apply.getProcess_instance_id());
            task.setProcessdefid(apply.getTask().getProcessDefinitionId());
            task.setReason(apply.getReason());
            task.setStart_time(apply.getStart_time());
            task.setTaskcreatetime(apply.getTask().getCreateTime());
            task.setTaskid(apply.getTask().getId());
            task.setTaskname(apply.getTask().getName());
            tasks.add(task);
        }
        grid.setTotal(totalsize);
        grid.setRows(tasks);
        return grid;
    }

    @ApiOperation("获取人事审批待办列表")
    @RequestMapping(value = "/hrtasklist", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<LeaveTask> gethrtasklist(HttpSession session, @RequestParam("current") int current,
                                             @RequestParam("rowCount") int rowCount) {
        DataGrid<LeaveTask> grid = new DataGrid<LeaveTask>();
        grid.setRowCount(rowCount);
        grid.setCurrent(current);
        String username = (String) session.getAttribute("username");
        int firstrow = (current - 1) * rowCount;
        List<LeaveApply> results = leaveservice.getpagehrtask(username, firstrow, rowCount);
        int totalsize = leaveservice.getallhrtask(username);
        List<LeaveTask> tasks = new ArrayList<LeaveTask>();
        for (LeaveApply apply : results) {
            LeaveTask task = new LeaveTask();
            task.setApply_time(apply.getApply_time());
            task.setUser_id(apply.getUser_id());
            task.setEnd_time(apply.getEnd_time());
            task.setId(apply.getId());
            task.setLeave_type(apply.getLeave_type());
            task.setProcess_instance_id(apply.getProcess_instance_id());
            task.setProcessdefid(apply.getTask().getProcessDefinitionId());
            task.setReason(apply.getReason());
            task.setStart_time(apply.getStart_time());
            task.setTaskcreatetime(apply.getTask().getCreateTime());
            task.setTaskid(apply.getTask().getId());
            task.setTaskname(apply.getTask().getName());
            tasks.add(task);
        }
        grid.setRowCount(rowCount);
        grid.setCurrent(current);
        grid.setTotal(totalsize);
        grid.setRows(tasks);
        return grid;
    }

    @ApiOperation("获取销假任务列表")
    @RequestMapping(value = "/xjtasklist", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<LeaveTask> getXJtasklist(HttpSession session, @RequestParam("current") int current,
                                             @RequestParam("rowCount") int rowCount) {
        int firstrow = (current - 1) * rowCount;
        String userid = (String) session.getAttribute("username");
        List<LeaveApply> results = leaveservice.getpageXJtask(userid, firstrow, rowCount);
        int totalsize = leaveservice.getallXJtask(userid);
        List<LeaveTask> tasks = new ArrayList<LeaveTask>();
        for (LeaveApply apply : results) {
            LeaveTask task = new LeaveTask();
            task.setApply_time(apply.getApply_time());
            task.setUser_id(apply.getUser_id());
            task.setEnd_time(apply.getEnd_time());
            task.setId(apply.getId());
            task.setLeave_type(apply.getLeave_type());
            task.setProcess_instance_id(apply.getProcess_instance_id());
            task.setProcessdefid(apply.getTask().getProcessDefinitionId());
            task.setReason(apply.getReason());
            task.setStart_time(apply.getStart_time());
            task.setTaskcreatetime(apply.getTask().getCreateTime());
            task.setTaskid(apply.getTask().getId());
            task.setTaskname(apply.getTask().getName());
            tasks.add(task);
        }
        DataGrid<LeaveTask> grid = new DataGrid<LeaveTask>();
        grid.setRowCount(rowCount);
        grid.setCurrent(current);
        grid.setTotal(totalsize);
        grid.setRows(tasks);
        return grid;
    }

    @ApiOperation("获取调整休假申请任务列表")
    @RequestMapping(value = "/updatetasklist", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<LeaveTask> getupdatetasklist(HttpSession session, @RequestParam("current") int current,
                                                 @RequestParam("rowCount") int rowCount) {
        int firstrow = (current - 1) * rowCount;
        String userid = (String) session.getAttribute("username");
        List<LeaveApply> results = leaveservice.getpageupdateapplytask(userid, firstrow, rowCount);
        int totalsize = leaveservice.getallupdateapplytask(userid);
        List<LeaveTask> tasks = new ArrayList<LeaveTask>();
        for (LeaveApply apply : results) {
            LeaveTask task = new LeaveTask();
            task.setApply_time(apply.getApply_time());
            task.setUser_id(apply.getUser_id());
            task.setEnd_time(apply.getEnd_time());
            task.setId(apply.getId());
            task.setLeave_type(apply.getLeave_type());
            task.setProcess_instance_id(apply.getProcess_instance_id());
            task.setProcessdefid(apply.getTask().getProcessDefinitionId());
            task.setReason(apply.getReason());
            task.setStart_time(apply.getStart_time());
            task.setTaskcreatetime(apply.getTask().getCreateTime());
            task.setTaskid(apply.getTask().getId());
            task.setTaskname(apply.getTask().getName());
            tasks.add(task);
        }
        DataGrid<LeaveTask> grid = new DataGrid<LeaveTask>();
        grid.setRowCount(rowCount);
        grid.setCurrent(current);
        grid.setTotal(totalsize);
        grid.setRows(tasks);
        return grid;
    }

    @ApiOperation("使用任务id获取请假业务数据")
    @RequestMapping(value = "/dealtask", method = RequestMethod.POST)
    @ResponseBody
    public LeaveApply taskdeal(@RequestParam("taskid") String taskid, HttpServletResponse response) {
        Task task = taskservice.createTaskQuery().taskId(taskid).singleResult();
        ProcessInstance process = runservice.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
                .singleResult();
        LeaveApply leave = leaveservice.getleave(new Integer(process.getBusinessKey()));
        return leave;
    }

    @RequestMapping(value = "/activiti/task-deptleaderaudit", method = RequestMethod.GET)
    String url() {
        return "/activiti/task-deptleaderaudit";
    }

    @ApiOperation("完成部门领导审批待办")
    @RequestMapping(value = "/task/deptcomplete/{taskid}", method = RequestMethod.POST)
    @ResponseBody
    public MSG deptcomplete(HttpSession session, @PathVariable("taskid") String taskid, HttpServletRequest req) {
        String username = (String) session.getAttribute("username");
        Map<String, Object> variables = new HashMap<String, Object>();
        String approve = req.getParameter("deptleaderapprove");
        String hr = req.getParameter("hr");
        variables.put("deptleaderapprove", approve);
        variables.put("hr", hr);
        taskservice.claim(taskid, username);
        taskservice.complete(taskid, variables);
        return new MSG("success");
    }

    @ApiOperation("完成hr审批待办")
    @RequestMapping(value = "/task/hrcomplete/{taskid}", method = RequestMethod.POST)
    @ResponseBody
    public MSG hrcomplete(HttpSession session, @PathVariable("taskid") String taskid, HttpServletRequest req) {
        String userid = (String) session.getAttribute("username");
        Map<String, Object> variables = new HashMap<String, Object>();
        String approve = req.getParameter("hrapprove");
        variables.put("hrapprove", approve);
        taskservice.claim(taskid, userid);
        taskservice.complete(taskid, variables);
        return new MSG("success");
    }

    @ApiOperation("完成销假待办")
    @RequestMapping(value = "/task/reportcomplete/{taskid}", method = RequestMethod.POST)
    @ResponseBody
    public MSG reportbackcomplete(@PathVariable("taskid") String taskid, HttpServletRequest req) {
        String realstart_time = req.getParameter("realstart_time");
        String realend_time = req.getParameter("realend_time");
        leaveservice.completereportback(taskid, realstart_time, realend_time);
        return new MSG("success");
    }

    @ApiOperation("完成调整申请待办")
    @RequestMapping(value = "/task/updatecomplete/{taskid}", method = RequestMethod.POST)
    @ResponseBody
    public MSG updatecomplete(@PathVariable("taskid") String taskid, @ModelAttribute("leave") LeaveApply leave,
                              @RequestParam("reapply") String reapply) {
        leaveservice.updatecomplete(taskid, leave, reapply);
        return new MSG("success");
    }

    @RequestMapping(value = "/getfinishprocess", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<HistoryProcess> getHistory(HttpSession session, @RequestParam("current") int current,
                                               @RequestParam("rowCount") int rowCount) {
        String userid = (String) session.getAttribute("username");
        HistoricProcessInstanceQuery process = histiryservice.createHistoricProcessInstanceQuery()
                .processDefinitionKey("leave").startedBy(userid).finished();
        int total = (int) process.count();
        int firstrow = (current - 1) * rowCount;
        List<HistoricProcessInstance> info = process.listPage(firstrow, rowCount);
        List<HistoryProcess> list = new ArrayList<HistoryProcess>();
        for (HistoricProcessInstance history : info) {
            HistoryProcess his = new HistoryProcess();
            String bussinesskey = history.getBusinessKey();
            LeaveApply apply = leaveservice.getleave(Integer.parseInt(bussinesskey));
            his.setLeaveapply(apply);
            his.setBusinessKey(bussinesskey);
            his.setProcessDefinitionId(history.getProcessDefinitionId());
            list.add(his);
        }
        DataGrid<HistoryProcess> grid = new DataGrid<HistoryProcess>();
        grid.setCurrent(current);
        grid.setRowCount(rowCount);
        grid.setTotal(total);
        grid.setRows(list);
        return grid;
    }

    @RequestMapping(value = "/historyprocess", method = RequestMethod.GET)
    public String history() {
        return "activiti/historyprocess";
    }

    @ApiOperation("使用流程实例编号获取历史流程数据")
    @RequestMapping(value = "/processinfo", method = RequestMethod.POST)
    @ResponseBody
    public List<HistoricActivityInstance> processinfo(@RequestParam("instanceid") String instanceid) {
        List<HistoricActivityInstance> his = histiryservice.createHistoricActivityInstanceQuery()
                .processInstanceId(instanceid).orderByHistoricActivityInstanceStartTime().asc().list();
        return his;
    }

    @ApiOperation("使用业务号获取历史流程数据")
    @RequestMapping(value = "/processhis", method = RequestMethod.POST)
    @ResponseBody
    public List<HistoricActivityInstance> processhis(@RequestParam("ywh") String ywh) {
        String instanceid = histiryservice.createHistoricProcessInstanceQuery().processDefinitionKey("purchase")
                .processInstanceBusinessKey(ywh).singleResult().getId();
        List<HistoricActivityInstance> his = histiryservice.createHistoricActivityInstanceQuery()
                .processInstanceId(instanceid).orderByHistoricActivityInstanceStartTime().asc().list();
        return his;
    }

    @RequestMapping(value = "myleaveprocess", method = RequestMethod.GET)
    String myleaveprocess() {
        return "activiti/myleaveprocess";
    }

    @ApiOperation("使用executionid追踪流程图进度")
    @RequestMapping(value = "traceprocess/{executionid}", method = RequestMethod.GET)
    public void traceprocess(@PathVariable("executionid") String executionid, HttpServletResponse response)
            throws Exception {
        ProcessInstance process = runservice.createProcessInstanceQuery().processInstanceId(executionid).singleResult();
        BpmnModel bpmnmodel = rep.getBpmnModel(process.getProcessDefinitionId());
        List<String> activeActivityIds = runservice.getActiveActivityIds(executionid);
        DefaultProcessDiagramGenerator gen = new DefaultProcessDiagramGenerator();
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
        List<HistoricActivityInstance> historicActivityInstances = histiryservice.createHistoricActivityInstanceQuery()
                .executionId(executionid).orderByHistoricActivityInstanceStartTime().asc().list();
        // 计算活动线
        List<String> highLightedFlows = leaveservice
                .getHighLightedFlows(
                        (ProcessDefinitionEntity) ((RepositoryServiceImpl) rep)
                                .getDeployedProcessDefinition(process.getProcessDefinitionId()),
                        historicActivityInstances);

        InputStream in = gen.generateDiagram(bpmnmodel, "png", activeActivityIds, highLightedFlows, "宋体", "宋体", null,
                null, 1.0);
        // InputStream in=gen.generateDiagram(bpmnmodel, "png",
        // activeActivityIds);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);
    }

    @RequestMapping(value = "myleaves", method = RequestMethod.GET)
    String myleaves() {
        return "activiti/myleaves";
    }

    @ApiOperation("我发起的请假流程")
    @RequestMapping(value = "setupprocess", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid<LeaveApply> setupprocess(HttpSession session, @RequestParam("current") int current,
                                             @RequestParam("rowCount") int rowCount) {
        String username = (String) session.getAttribute("username");
        List<LeaveApply> list = leaveservice.getPageByApplyer(username, current, rowCount);
        for (LeaveApply apply : list) {
            ProcessInstance process = runservice.createProcessInstanceQuery().processInstanceId(apply.getProcess_instance_id()).singleResult();
            if (process == null) {
                apply.setState("已结束");
                apply.setActivityid("无");
            } else {
                apply.setState("运行中");
                apply.setActivityid(process.getActivityId());
            }
        }
        DataGrid<LeaveApply> grid = new DataGrid<LeaveApply>();
        grid.setCurrent(current);
        grid.setRowCount(rowCount);
        grid.setTotal(leaveservice.getAllByApplyer(username));
        grid.setRows(list);
        return grid;
    }

}
