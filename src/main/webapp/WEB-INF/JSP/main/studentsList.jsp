<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script>
    function modifingStudents() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select students");
            return;
        }

        if (items.length > 1) {
            alert("Please select anly one student");
            return;
        } else {
            var id = $(items[0]).attr("id");
            console.log("id=" + id);
            $('.admin_table').append('<form id="form_modif" method="GET" action="${CONTEXT}${CURRENT_MAPPING}/studentsModifying.php">   <input type="hidden" name="id" value=' + id + '>  </form>');
            $('#form_modif').submit();
        }
    }
</script>

<script>
    $(document).ready(function () {
        $("#deleteStudents").on("click", function () {
            var listCheckbox = $.map($(":checkbox:checked"), function (el) {
                return $(el).val();
            });

            if(listCheckbox.length!=0){
                $('.admin_table').append('<form id="form_modif" method="POST" action="${CONTEXT}${CURRENT_MAPPING}/studentsDelete.php">   <input type="hidden" name="listCheckbox" value=' + listCheckbox + '>  </form>');
                $('#form_modif').submit();
                return;
            } alert("Please select students");

        });
    });
</script>


<script>
    $(document).ready(function () {
        $("#createStudents").on("click", function () {
            $('.admin_table').append('<form id="form_modif" method="GET" action="${CONTEXT}${CURRENT_MAPPING}/studentsCreate.php"> </form>');
            $('#form_modif').submit();
        });
    });
</script>


<div class="header_create_form">
    <div class="header_home_1" align="left">
        <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">Назад</a>
    </div>
</div>

<section id="content_style">
    <div class="admin_table">
        <table>
            <tr>
                <td>
                    <input class="button_style" type="button" name="view_student_progress"
                           value="Просмотреть успеваемость выбранных студентов">
                </td>
                <c:if test="${CURRENT_ROLE eq 1}">
                <td>
                    <input id="createStudents" class="button_style" type="submit" value="Создать студента">
                </td>
            </tr>
            <tr>
                <td>
                    <input class="button_style" type="submit" value="Модифицировать выбранного студента"
                           onclick="modifingStudents()">
                </td>
                <td>
                    <input id="deleteStudents" class="button_style" type="submit" value="Удалить выбранных студентов">
                </td>
            </tr>
            </c:if>
            <tr>
                <td colspan="2">
                   <p class="error_message">
                       <jsp:include page="../modules/validationMessage.jsp"/>
                   </p>
                </td>
            </tr>
        </table>
    </div>

    <div id="header_list">Students list</div>

    <form>
        <table class="table_student" border="2" cellpadding="10">
            <tr class="string_table" bgcolor="#a9a9a9">
                <td>

                </td>
                <td>
                    Фамилия
                </td>
                <td>
                    Имя
                </td>
                <td>
                    Группа
                </td>
                <td>
                    Дата поступления
                </td>
            </tr>
            <c:forEach items="${students}" var="st">
                <tr>
                    <td><input type='checkbox' id="${st.id}" value="${st.id}"></td>
                    <td>${st.first_name}</td>
                    <td>${st.name}</td>
                    <td>${st.group}</td>
                    <td>${st.date}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</section>
