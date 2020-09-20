<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>出勤・退勤時間 一覧</h2>
        <table id="attendances_list">
        <tbody>
           <tr>
               <th>NO.</th>
               <th>氏名</th>
               <th>日付</th>
               <th>出勤時間</th>
               <th>退勤時間</th>
          </tr>
          <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="attendance_id"><c:out value="${attendance.id}" /></td>
                        <td class="attendance_name"><c:out value="${attendance.name}" /></td>
                        <td class="attendance_work_date"><fmt:formatDate value="${attendance.work_date }" pattern ="MM-dd" /></td>
                        <td class="attendance_arrive"><fmt:formatDate value="${attendance.arrive}" pattern="HH-mm" /></td>
                        <td class="attendance_finish"><fmt:formatDate value="${attendance.finish}" pattern="HH-mm" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${attedances_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/attendances/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/attendances/new' />">新規勤怠の登録</a></p>

    </c:param>
</c:import>