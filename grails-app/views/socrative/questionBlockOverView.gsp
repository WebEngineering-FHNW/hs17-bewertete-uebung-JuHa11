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
                <li style="float:right"><a class="active" href="#about">About</a></li>
            </ul>
        </nav>

        <div>
            <table style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>#Questions</th>
                        <th>Start</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qblocks}">
                        <tr>
                            <td>${it.id}</td>
                            <td>${it.name}</td>
                            <td>${it.numberOfQuestions}</td>
                            <td>start</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div>
            <a href="${createLink(uri:'/socrative/addQuestionBlock')}"><button>add</button></a>
        </div>
    </body>
</html>