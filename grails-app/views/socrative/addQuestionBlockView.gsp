<html>
    <head>
        <meta name="AddQuestionblock" content="main">
        <title>Add Questionblock</title>
        <link rel="stylesheet" type="text/css" href="${resource(file: "bootstrap.css")}" />
        <link rel="stylesheet" type="text/css" href="${resource(file: "own.css")}" />
        <asset:javascript src="jquery-2.2.0.min.js" />
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="/socrative/index">Overview</a></li>
            </ul>
        </nav>

        <g:form controller="socrative" action="save">
            <div>
                <g:if test="${title != null}">
                    <bigtitle>Add a question to:</bigtitle> ${title}<br>
                    <input class="input" type="text" class="headertext" value="${title}" name="name" hidden><br>
                    <input name="id" value="${id}" type="hidden"> <br>
                </g:if>
                <g:else>
                    <bigtitle>Title of the questionblock: </bigtitle>
                    <input class="input" type="text" class="headertext" name="name" required><br>
                </g:else>
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
                </table><br>
                <bigtitle>Explanation:</bigtitle><input class="input" type="text" name="explanation">
            </div>
            <br>
            <button type="submit" name="save">save</button>
            <button type="submit" name="addmore">add more questions</button>
        </g:form>
    </body>


    <script type="text/javascript">
        /*
         * https://www.w3schools.com/jquery/jquery_ref_selectors.asp
         */
        $(document).ready(function () {
            $(":submit").click(function() {
                console.log("Called!");
                checked = $("input[type=checkbox]:checked").length;
                console.log("Checked: " + checked);
                if(!checked) {
                    alert("You must check at least one checkbox.");
                    return false;
                }
            });
        });
    </script>
</html>