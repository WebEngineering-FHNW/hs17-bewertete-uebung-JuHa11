<html>
    <head>
        <meta name="AddQuestionblock" content="main">
        <title>Add Questionblock</title>
        <link rel="stylesheet" type="text/css" href="${resource(file: "own.css")}" />
    </head>
    <body>
        <ul>
            <li><a href="/socrative/index">Overview</a></li>
            <li><a href="#news">News</a></li>
            <li><a href="#contact">Contact</a></li>
            <li style="float:right"><a class="active" href="#about">About</a></li>
        </ul>

        <g:form controller="socrative" action="save">
            <div>
                Title: <input type="text" name="name">
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th></th>
                        <th>Correct</th>
                        <th>Question</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Answer 1</td>
                            <td><input type="checkbox" name="answer1correct"></td>
                            <td><input type="text" name="answer"></td>
                        </tr>
                        <tr>
                            <td>Answer 2</td>
                            <td><input type="checkbox" name="answer2correct"></td>
                            <td><input type="text" name="answer"></td>
                        </tr>
                        <tr>
                            <td>Answer 3</td>
                            <td><input type="checkbox" name="answer3correct"></td>
                            <td><input type="text" name="answer"></td>
                        </tr>
                        <tr>
                            <td>Answer 4</td>
                            <td><input type="checkbox" name="answer4correct"></td>
                            <td><input type="text" name="answer"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <button type="submit" name="save">save</button>
            <button type="submit" name="addmore">add more questions</button>
        </g:form>
    </body>
</html>