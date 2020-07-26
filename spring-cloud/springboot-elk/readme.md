ELK搭建日志收集分析系统：

Elasticsearch+Logstash+Kibana

Logstash：日志收集工具，可以从本地磁盘，网络服务（自己监听端口，接受用户日志），消息队列中收集各种各样的日志，然后进行过滤分析，并将日志输出到Elasticsearch中。

Elasticsearch：日志分布式存储/搜索工具，原生支持集群功能，可以将指定时间的日志生成一个索引，加快日志查询和访问。

Kibana：可视化日志Web展示工具，对Elasticsearch中存储的日志进行展示，还可以生成炫丽的仪表盘。