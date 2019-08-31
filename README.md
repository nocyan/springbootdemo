# springbootdemo

项目暂停，转战shiro

## user

### restful接口对第三方登录的处理：
1. 数据库设计：采用userAuth-user多对一结构，兼容用户名、邮箱、手机号、第三方登录等登录方式
2. 第三方登录接口编写：
* AuthTypeEnum(记录登录方式，同时记录第三方登录接口对应的provider，便于反射调用provider的验证方法)
* DTO(方便json处理第三方网站返回的用户信息)
* provider(具体接入第三方登录业务，同时提供验证accessToken的方法)
1. url设计：将第三方接口的回调url映射到auth.html，前端调用ajax传递到后端进行accessToken验证
