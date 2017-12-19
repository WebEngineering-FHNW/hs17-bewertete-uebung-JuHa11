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
                <li><a href="${createLink(uri:'/socrative/addQuestionBlock')}">Add Questionblock</a></li>
            </ul>
        </nav>

        <div class="content">
            <p><bigtitle>Overview</bigtitle></p>
            <g:if test="${qblocks.isEmpty()}">
                <p>There are no Questionblocks yet. Add one!</p>
            </g:if>
            <g:else>
                <div class="row" style="margin-left: 10%; margin-right: 5%;">
                    <g:each in="${qblocks}">
                            <section style="width: 100%;">
                                <boxstartseite>
                                    <smalltitle>${it.name}</smalltitle>
                                    <p>#Questions: ${it.numberOfQuestions}</p>
                                    <p>Highscore: ${it.highscore} / ${it.getNumberOfQuestions()}</p>
                                    <p><g:link controller="socrative" action="startQuestionset" params="[questionblockID:it.id]" > start </g:link><p>
                                    <p><g:link controller="socrative" action="getDetails" params="[questionblockID:it.id]" > details </g:link><p>
                                </boxstartseite>
                            </section>
                    </g:each>
                </div>
            </g:else>
            <div>
                <a href="${createLink(uri:'/socrative/addQuestionBlock')}"><button>add</button></a>
            </div>
        </div>
    </body>
</html>