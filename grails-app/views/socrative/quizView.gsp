
<g:if test="${question != null}">
    <p>
        <g:form>
            ${question.question}<br>
            <td><input type="checkbox" name="answer1correct"></td>
            ${question.answer1.answer}<br>
            <td><input type="checkbox" name="answer2correct"></td>
            ${question.answer2.answer}<br>
            <td><input type="checkbox" name="answer3correct"></td>
            ${question.answer3.answer}<br>
            <td><input type="checkbox" name="answer4correct"></td>
            ${question.answer4.answer}<br>
        </g:form>
    </p>
    <g:link controller="socrative" action="nextQuestion" params="[ index: index, questionblockID: questionblockID]" > weiter </g:link>
</g:if>
<g:else>
    Fertig!
</g:else>

