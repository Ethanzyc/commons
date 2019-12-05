# commons

#### 背景：

自己会经常写一些小项目，比如查询监控比特币的小程序、给朋友4s店写的财务管理和2c的小程序（还未上线）等等，自己搭架子的过程中踩了不少坑，也着实有意思，每一坑背后都是不小的收获，最后整理出这么一套，基本够用，但里面很多东西不够严谨，希望能不断完善，也欢迎能看到这个的开发者们能提出意见，谢谢！等完善到自己满意的程度后会出一份从0搭建的教程。

  	* 整体框架接口为 Springboot 2.1.x + MyBatis(mybatis-plus 辅助开发) ；
  	* 数据库方面：
        	  *  Mysql 版本为 8.0，官方宣称比5.7性能更好，暂时还没遇到什么坑；
           *  通过 flyway 控制表结构版本信息，第一次运行程序的时候会生成表结构；
           *  数据库连接池为 druid，虽然速度不如 hikari，但监控能力较为强大，自己也继承 druid 中的 StatFilter 类来实现自己打印/处理慢 sql 的逻辑；
           *  利用 mybatis-plus 中的表结构逆向工程 + velocity 模版来自动生成代码（mapper + xml + service）
  	* redis 、mongo 都使用的是 Springboot 官方集成，其中 redis 的连接池为 jedis；
  	* 权限控制为 SpringSecurity + jwt，其中 jwt 的标准流程里会加一步将 token 经过 md5 签名后存储在 redis 中，把 md5后的token 返回给前端，防止 jwt 生成的 token 被解析后泄露信息；
  	* 任务调度采用的是比较常用的 quartz，配合任务表实现动态定时任务；
  	* rest 请求工具为 web 包中自带的 RestTemplate，http 连接池改为 okhttp；
  	* 消息队列采用 RabbitMQ（下一步加入）；
  	* 第三方服务还包括：腾讯云短信，七牛云对象存储；
  	* 工具包包括：apache 的 commons-lang3，google 的 guava ， hutool；
  	* 服务器部署为编写 shell 脚本通过  Jenkins 实现自动化部署（脚本改进后上传）。