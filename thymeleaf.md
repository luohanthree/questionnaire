# thymeleaf

在使用thymealeaf时须在`<html>`标签添加

```html
xmlns:th="http://www.thymeleaf.org"
```

### 标准表达式

1. 变量表达式：`${..}`
2. 选择变量表达式：`*{}`
3. 链接表达式：`@{}`
4. 国际化表达式：`#{}`
5. 片段应用表达式：`~{}`

#### th属性

| 属性        | 描述                                                         | 示例                                                         |
| ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| th:id       | 替换 HTML 的 id 属性                                         | `<input  id="html-id"  th:id="thymeleaf-id"  />`             |
| th:text     | 文本替换，转义特殊字符                                       | `<h1 th:text="hello，bianchengbang" >hello</h1>`             |
| th:utext    | 文本替换，不转义特殊字符                                     | `<div th:utext="'<h1>欢迎来到编程帮！</h1>'" >欢迎你</div>`  |
| th:object   | 在父标签选择对象，子标签使用 *{…} 选择表达式选取值。  没有选择对象，那子标签使用选择表达式和 ${…} 变量表达式是一样的效果。  同时即使选择了对象，子标签仍然可以使用变量表达式。 | `<div th:object="${session.user}" >    <p th:text="*{fisrtName}">firstname</p></div>` |
| th:value    | 替换 value 属性                                              | `<input th:value = "${user.name}" />`                        |
| th:with     | 局部变量赋值运算                                             | `<div th:with="isEvens = ${prodStat.count}%2 == 0"  th:text="${isEvens}"></div>` |
| th:style    | 设置样式                                                     | `<div th:style="'color:#F00; font-weight:bold'">编程帮 www.biancheng.net</div>` |
| th:onclick  | 点击事件                                                     | `<td th:onclick = "'getInfo()'"></td>`                       |
| th:each     | 遍历，支持 Iterable、Map、数组等。                           | `<table>    <tr th:each="m:${session.map}">        <td th:text="${m.getKey()}"></td>        <td th:text="${m.getValue()}"></td>    </tr></table>` |
| th:if       | 根据条件判断是否需要展示此标签                               | `<a th:if ="${userId == collect.userId}">`                   |
| th:unless   | 和 th:if 判断相反，满足条件时不显示                          | ` <div th:unless="${m.getKey()=='name'}" ></div>`            |
| th:switch   | 与 Java 的 switch case语句类似  通常与 th:case 配合使用，根据不同的条件展示不同的内容 | `<div th:switch="${name}">    <span th:case="a">编程帮</span>    <span th:case="b">www.biancheng.net</span></div>` |
| th:fragment | 模板布局，类似 JSP 的 tag，用来定义一段被引用或包含的模板片段 | `<footer th:fragment="footer">插入的内容</footer>`           |
| th:insert   | 布局标签；  将使用 th:fragment 属性指定的模板片段（包含标签）插入到当前标签中。 | `<div th:insert="commons/bar::footer"></div>`                |
| th:replace  | 布局标签；  使用 th:fragment 属性指定的模板片段（包含标签）替换当前整个标签。 | `<div th:replace="commons/bar::footer"></div>`               |
| th:selected | select 选择框选中                                            | `<select>    <option>---</option>    <option th:selected="${name=='a'}">        编程帮    </option>    <option th:selected="${name=='b'}">        www.biancheng.net    </option></select>` |
| th:src      | 替换 HTML 中的 src 属性                                      | `<img  th:src="@{/asserts/img/bootstrap-solid.svg}" src="asserts/img/bootstrap-solid.svg" />` |
| th:inline   | 内联属性；  该属性有 text、none、javascript 三种取值，  在 <script> 标签中使用时，js 代码中可以获取到后台传递页面的对象。 | `<script type="text/javascript" th:inline="javascript">    var name = /*[[${name}]]*/ 'bianchengbang';    alert(name)</script>` |
| th:action   | 替换表单提交地址                                             | `<form th:action="@{/user/login}" th:method="post"></form>`  |

