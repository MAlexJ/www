<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="header_create_form">
    <div class="header_home_1" align="left">
        <a href="${CONTEXT}${CURRENT_MAPPING}/home.php">Назад</a>
    </div>
</div>

<script>

    $(document).ready(function () {
        $("#selectTerm").on("click", function () {
            var selectId = $("#select_opt").val();
            $('.list_4').append('<form id="form_modif" method="POST" action="${CONTEXT}${CURRENT_MAPPING}/termsList.php">   <input type="hidden" name="select" value=' + selectId + '>  </form>');
            $('#form_modif').submit();
        });
    });

    $(document).ready(function () {
        $("#createTerm").on("click", function () {
            $('.admin_table_disc').append('<form id="form_modif" method="get" action="${CONTEXT}${CURRENT_MAPPING}/termCreate.php"> </form>');
            $('#form_modif').submit();
        });
    });

    $(document).ready(function () {
        $("#modifyingTerm").on("click", function () {
            var selectId = $("#select_opt").val();
            $('.admin_table_disc').append('<form id="form_modif" method="get" action="${CONTEXT}${CURRENT_MAPPING}/termsModifying.php">   <input type="hidden" name="select" value=' + selectId + '>  </form>');
            $('#form_modif').submit();
        });
    });

    $(document).ready(function () {
        $("#deleteTerm").on("click", function () {
            var selectId = $("#select_opt").val();
            $('.admin_table_disc').append('<form id="form_modif" method="POST" action="${CONTEXT}${CURRENT_MAPPING}/termsDelete.php">   <input type="hidden" name="select" value=' + selectId + '>  </form>');
            $('#form_modif').submit();
        });
    });


</script>


<section id="content_style">

    <div class="list_2"> Select the semester</div>
    <div class="list_3">

        <select id="select_opt" name="select">
            <c:forEach items="${terms}" var="idTerms">
                <c:choose>
                    <c:when test="${idSelected eq idTerms.id}">
                        <option selected value="${idTerms.id}">Term ${idTerms.id}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${idTerms.id}">Term ${idTerms.id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

    </div>
    <div class="list_4"><input id="selectTerm" class="button_opt" type="button" value="Select"></div>

    <div class="list_1">The duration of the semester: ${firstTerm.duration}</div>

    <div class="list">The list of disciplines of the semester</div>

    <table class="table_term" border="2" cellpadding="10">
        <tr bgcolor="#a9a9a9">
            <td>List disciplines the Term</td>
        </tr>
        <c:forEach items="${firstTerm.discipline}" var="ds">
            <tr>
                <td>${ds.name}</td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${CURRENT_ROLE eq 1}">
        <div class="admin_table_disc">
            <table>
                <tr>
                    <td>
                        <input id="createTerm" class="button_style" type="button" value="Create Term">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="modifyingTerm" class="button_style" type="button"
                               value="Modifying select Term">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="deleteTerm" class="button_style" type="button"
                               value="Delete this Term">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p class="error_message">
                            <jsp:include page="../modules/validationMessage.jsp"/>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

</section>