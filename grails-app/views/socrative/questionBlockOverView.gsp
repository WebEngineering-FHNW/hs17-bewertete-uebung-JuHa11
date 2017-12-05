<html>
    <head>
        <meta name="Overview" content="main">
        <title>Overview</title>
        <link rel="stylesheet" type="text/css" href="${resource(file: "own.css")}" />
    </head>

    <body>
        <div>
            <table style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>#Questions</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qblocks}">
                        <tr>
                            <td>${it.id}</td>
                            <td>${it.name}</td>
                            <td>${it.numberOfQuestions}</td>
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