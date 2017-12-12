
<g:if test="${question != null}">
    <p>
        ${question.question}<br>
        ${question.answer1.answer}<br>
        ${question.answer2.answer}<br>
        ${question.answer3.answer}<br>
        ${question.answer4.answer}<br>
    </p>
    <g:link controller="socrative" action="nextQuestion" params="[ index: index, questionblockID: questionblockID]" > weiter </g:link>
</g:if>
<g:else>
    Fertig!
</g:else>

