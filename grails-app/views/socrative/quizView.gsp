<html>
    <head>
        <meta name="Overview" content="main">
        <title>Overview</title>
        <link rel="stylesheet" type="text/css" href="${resource(file: "own.css")}" />
    </head>

    <body>
        <nav>
            <ul>
                <li><a href="/socrative/index">Overview</a></li>
                <li><a href="#news">News</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </nav>
        <g:if test="${lastquestion != null}">
            ${lastquestion}
        </g:if>
        <g:if test="${question != null}">
            <p>
                <g:form controller="socrative" action="nextQuestion">
                    <bigtitle>${question.question}</bigtitle><p>
                    <box>
                        <input type="checkbox" name="answer1correct">
                        ${question.answer1.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer2correct"></td>
                        ${question.answer2.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer3correct"></td>
                        ${question.answer3.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer4correct"></td>
                        ${question.answer4.answer}<br>
                    </box>
                    <input name="index" value="${index}" type="hidden"> <br>
                    <input name="questionblockID" value="${questionblockID}" type="hidden"> <br>
                    <button type="submit" name="continue" >next</button>
                </g:form>
            </p>
        </g:if>
        <g:else>
            Fertig!
        </g:else>
    </body>
</html>

