# 数据库常用操作
## 1 导入多个数据库
1. 运行merge.py，它会把所有sql的内容合并到aaa.sql  
2. 使用mysql或navicat导入sql文件（导入前必须确定该数据库是否存在）  

mysql导入语句示例，```source ./new.sql```  
mysql导出语句示例，```mysqldump -u root -p 数据库名 > ./aaa.sql```  
<br/>


## 2 