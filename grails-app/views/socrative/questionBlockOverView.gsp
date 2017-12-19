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
                <li><g:link controller="socrative" action="index">Overview</g:link></li>
                <li><g:link controller="socrative" action="addQuestionBlock">Add Questionblock</g:link></li>
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
                                    <boxtext>
                                        <p>#Questions: ${it.numberOfQuestions}</p>
                                        <p>Highscore: ${it.highscore} / ${it.getNumberOfQuestions()}</p>
                                    </boxtext>
                                    <p>
                                        <g:link controller="socrative" action="startQuestionset" params="[questionblockID:it.id]" >
                                            <button class="smallbutton">start</button>
                                        </g:link>
                                        <g:link controller="socrative" action="getDetails" params="[questionblockID:it.id]" >
                                            <button class="smallbutton">details</button>
                                        </g:link>
                                    <p>
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