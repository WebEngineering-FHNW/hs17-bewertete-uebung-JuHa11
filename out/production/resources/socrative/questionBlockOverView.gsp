<html>
    <head>
        <meta name="Overview" content="main">
        <title>Overview</title>
        <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: powderblue;
        }

        button {
            border: 2px solid lightgrey;
            border-radius: 5px;
        }
        </style>
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
            <a href="/"><button>add</button></a>
        </div>
    </body>
</html>