
校园问答网

涉及的关键点：

1，开发工具使用了IntelliJ IDEA,使用GitHub进行代码管理；

2，整体框架采用了spring框架，在spring中让前后端开发分离；

3，数据库前后的读取使用了mybatis使系统与数据库结合；

4，登录注册方面，为了保证网站安全，在密码中加入了salt，防止因为密文相似而被别人破解，为了防止注册机注册大量账号，注册时也加入了邮箱激活验证机制，
确保邮箱为真人使用且使用人可登录该邮箱。在登录注册中使用了拦截器的思想。自动登录方面，在cookie里面加了token，登录时下发token，与用户信息关联起来，
token信息存储优化，不再使用mysql数据库，而是使用redis作为数据库；

5，敏感词过滤。在这方面使用了前缀树思想，结合状态机，提高了性能，也为系统增加了可扩展性；

6，异步框架，底层数据结构使用redis队列实现线程同步。设置优先队列，处理紧急任务。具体执行时使用eventhandler类控制；

7，动态推拉机制。推机制实时性快，拉机制节省空间。我们网站采用先推后拉——先由信源及时推送公共信息，再由用户有针对性地拉取个性化信息；

8，爬虫与搜索。网站原始数据采用爬虫从其他网站获得部分数据，使用多线程爬虫，提高效率，使用代理服务器和换IP方法来防止被服务器封禁，搜索采用solr,
涉及中文分词算法；

9，单元测试和部署。在Linux系统上搭建服务器，采用nginx反向代理；
