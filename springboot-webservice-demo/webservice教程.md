## webservice教程
### 1.安装使用webservice-server
#### 1.1 eclipse的maven环境准备
![](./doc-images/maven-settings设置1.png)
![](./doc-images/maven-settings设置2.png)

#### 1.2 eclipse导入webservice-server的maven工程
![](./doc-images/eclipse通过maven模式导入案例代码.png)
![](./doc-images/输入案例代码的路径.png)

#### 1.3 maven项目的准备工作
![](./doc-images/项目工程准备使用mvn build初始化环境.png)
![](./doc-images/maven-build 接口服务,以备启动.png)

#### 1.4 启动webservice-server,并查看webservice接口服务效果
![](./doc-images/启动webservice样例.png)
![](./doc-images/webservice服务案例url.png)
![](./doc-images/webservice具体接口地址.png)

### 2.安装使用webservice-client

#### 2.1 eclipse的maven环境准备
参照 1.1 (略)

#### 2.2 准备javeee环境,使用webservice-client代码工具
![](./doc-images/webservice-client的准备工作-准备javeee环境.png)
![](./doc-images/webservice-client的准备工作-准备javeee环境2.png)

#### 2.3 创建webservice-client工程

##### 2.3.1 webservice-client创建1-新建普通java项目
![](./doc-images/webservice-client创建1-新建普通java项目.png)

##### 2.3.1 webservice-client创建2-利用javaEE工具,创建webservice-client
![](./doc-images/webservice-client创建2-新建选择webservice-client.png)
![](./doc-images/webservice-client创建3-输入对应接口地址.png)
![](./doc-images/webservice-client创建4-选择代码导出工程.png)
![](./doc-images/webservice-client创建5-代码架构.png)

##### 2.3.3 使用代理类,XXXstub调用RPC的API
![](./doc-images/使用webservice-client的api.png)