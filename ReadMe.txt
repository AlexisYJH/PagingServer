下载安装 IDEA 2021 破解版 ，含idea安装包和破解补丁
https://www.cnblogs.com/technicist/p/15229615.html

Tomcat安装步骤及详细配置教程
https://blog.csdn.net/qq_41701956/article/details/126693711

用IDEA创建一个Maven项目并写出第一个servlet
https://blog.csdn.net/weixin_43654123/article/details/120824388
1. IDEA创建Maven的web项目
2. 添加servlet依赖
3. 部署Tomcat
    Add New Configuration——Tomcat Server Local——(添加Tomcat)——Fix
4. 第一个servlet
    java——new——Servlet

maven库，添加依赖
https://mvnrepository.com/

address localhost:8080 is already in use（端口被占用）
https://blog.csdn.net/qq_45720042/article/details/120854916

Servlet：
- PositionDataSourceServlet
//http://localhost:8080/pagingserver/pds.do?start=0&count=8

- PageKeyedDataSourceServlet
//http://localhost:8080/pagingserver/pkds.do?page=1&pagesize=8

- ItemKeyedDataSourceServlet
//http://localhost:8080/pagingserver/ikds.do?since=0&pagesize=8

- CoroutineDataSourceServlet
//http://localhost:8080/pagingserver/cds.do?since=0&pagesize=8