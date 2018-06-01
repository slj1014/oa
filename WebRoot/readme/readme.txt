1.struts回显示数据的时候，把对象放入map栈，也可以用el表达式访问
2.超链接用<s:a action="roleAction_addUI">
3.删除<s:a action="roleAction_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
4.表单使用<s:form>标签，把查出来的bean放入栈顶，就可以使用name进行回显了
5.增，删，该，使用重定向(重定向地址会发生改变)
6.只有事务注解可以被继承
7.在注释的时候标上todo fixme会在task中显示
8.当涉及到多张表的增加，可以在Action定义id（回显的时候会早当前的action的）
9.在当前的请求中用%｛parentsId｝就可以获取当前反问的参数
10.可以在struts.xml中使用${}传递参数,如果是转发，则可以是地址栏参数
13.bat文件，window下面的批处理文件（初始化文件）
14.静态不能使用代理
15.$("[name='root']").attr("checked",this.checked)
16.jquery的几个方法
      查找上一级元素：parent(expr)
      查找下一级元素:childern(expr)
      查找兄弟元素：siblings(expr)
      查找所有的上级元素：parents(expr)
      查找所有的后辈元素：find(expr)
      查找上一个（兄）元素：prev(expr)
      查找下一个（弟）元素:next(expr)
17.不是一个请求可能会出现懒加载异常，把懒加载lazy改成"false"
18.在ognl表达式中可以使用domain实体类中的函数    
19.修改原来struts标签的元素
                  自定义标签：doStartTag(),doEndTag()
20.配置全局的结果集：
    <global-results>
  		<result name="loginUI">/WEB-INF/jsp/userAction/loginUI.jsp</result>
     </global-results> 
21.获取struts访问的url  
      	String nameSpaceName=invocation.getProxy().getNamespace();
		String actionName=invocation.getProxy().getActionName();   
22.窗口
    // 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
23.desc降序3,2,1 asc升序1，2，3
24.status="status"状态的使用。#status.first
25. 一对一，在有外键的一方
      <many-to-one name="lastTopic" class="oa.domain.Topic" column="lastTopicId" unique="true"> </many-to-one>
26.struts 日期标签
     <s:date name="%{lastTopic.postTime}" format="yyyy-MM-dd HH:mm:ss"/>
27.小技巧：前面图标的时候可以使用type.gif就不用s:if判断了
28.循环		
					<s:iterator begin="1" end="14" var="num">
29.select id,name,(case gender when  1 then '男' when 2 then '女' else '其他' end) form user;								<input type="radio" name="faceIcon" value="${num}" id="r_${num}"/>
30.注意:t.forum=?这里传的是对象
31.fckeditor这个是texteare



ad:快捷键：
   alt+左右方向键，返回前后
   alt+shift+a 快捷通道
   ctr+d删除行
   ctr+O显示当前类的属性
   