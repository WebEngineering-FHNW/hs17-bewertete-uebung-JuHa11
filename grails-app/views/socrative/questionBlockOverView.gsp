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
                <li><a href="#news">News</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </nav>

        <bigtitle>Overview</bigtitle>
        <div class="row" style="margin-left: 10%; margin-right: 5%;">
            <g:each in="${qblocks}">
                    <section style="width: 100%;">
                        <boxstartseite>
                            <smalltitle>${it.name}</smalltitle>
                            <p>${it.numberOfQuestions}</p>
                            <p><g:link controller="socrative" action="startQuestionset" params="[questionblockID:it.id]" > start </g:link><p>
                        </boxstartseite>
                    </section>
            </g:each>
        </div>
        <div>
            <a href="${createLink(uri:'/socrative/addQuestionBlock')}"><button>add</button></a>
        </div>
    </body>
</html>