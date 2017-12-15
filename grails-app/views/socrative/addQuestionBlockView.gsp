<html>
    <head>
        <meta name="AddQuestionblock" content="main">
        <title>Add Questionblock</title>
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

        <g:form controller="socrative" action="save">
            <div>
                <bigtitle>Title of the questionblock: </bigtitle>
                <input class="input" type="text" class="headertext" name="name" required><br>
                <bigtitle>Question: </bigtitle>
                <input class="input" type="text" class="headertext" name="question" required>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>Correct</th>
                        <th>Answer</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input class="input" type="checkbox" name="answer1correct"></td>
                            <td><input class="input" type="text" class="answerfield" name="answer" required></td>
                        </tr>
                        <tr>
                            <td><input class="input" type="checkbox" name="answer2correct"></td>
                            <td><input class="input" type="text" class="answerfield" name="answer" required></td>
                        </tr>
                        <tr>
                            <td><input class="input" type="checkbox" name="answer3correct"></td>
                            <td><input class="input" type="text" class="answerfield" name="answer" required></td>
                        </tr>
                        <tr>
                            <td><input class="input" type="checkbox" name="answer4correct"></td>
                            <td><input class="input" type="text" class="answerfield" name="answer" required></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <br>
            <button type="submit" name="save">save</button>
            <button type="submit" name="addmore">add more questions</button>
        </g:form>
    </body>
</html>