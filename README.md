# apicontroller

实现springMVC类似的功能

该项目只依赖了spring,日志

项目主要作用 1、执行方法的时，前后可以自己加逻辑处理 在ApiHander类handle方法中，method.invoke(controllerCls);前后加任何逻辑处理返回的结果啊，参数的处理啊 等等

注解为APIController也由spring 管理

测试在MainController中

可以改造成文档生成器啊