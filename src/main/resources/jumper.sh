#!/usr/bin/expect -f
set user xianxiaoming	
set host jumpserver.edianzuno.cn
set port 2222
set password SUye4U84YtesPKjHg
set timeout 300
spawn ssh  -p $port  $user@$host
expect "*assword:*"
send "$password\r"
interact
expect eof
