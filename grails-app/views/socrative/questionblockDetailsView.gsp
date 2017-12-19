<%@ page import="java.math.RoundingMode" %>
<html>
    <head>
        <meta name="Overview" content="main">
        <title>Overview</title>
        <link rel="stylesheet" type="text/css" href="${resource(file: "bootstrap.css")}" />
        <link rel="stylesheet" type="text/css" href="${resource(file: "own.css")}" />
    </head>

    <body>
    <nav>
        <ul>
            <li><a href="/socrative/index">Overview</a></li>
        </ul>
    </nav>
    <p>
        <bigtitle>Details ${questionblocktitle}</bigtitle><br>
    </p>
        <g:each in="${questions}">
            <section style="width: 100%;">
                <boxstartseite>
                    <p>
                       <smalltitle>${it.question}</smalltitle><br>
                    </p>
                    ${it.answer1.answer}:
                    ${it.statistic[0].setScale(2, RoundingMode.HALF_UP)}%
                    <g:if test="${it.answer1.correct}">correct</g:if>
                    <br>
                    ${it.answer2.answer}:
                    ${it.statistic[1].setScale(2, RoundingMode.HALF_UP)}%
                    <br>
                    ${it.answer3.answer}:
                    ${it.statistic[2].setScale(2, RoundingMode.HALF_UP)}%
                    <br>
                    ${it.answer4.answer}:
                    ${it.statistic[3].setScale(2, RoundingMode.HALF_UP)}%
                    <br>
                </boxstartseite>
            </section>
        </g:each>
    </body>
</html>