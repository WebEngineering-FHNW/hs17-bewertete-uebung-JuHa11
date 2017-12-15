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
        <g:if test="${lastquestion != null}">

            <!-- Trigger/Open The Modal -->
            <button id="openModalButton" hidden></button>

            <!-- The Modal -->
            <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                    <span class="close">weiter</span>
                    <p>
                    <g:if test="${lastquestion}">
                        Your answer is correct!
                    </g:if>
                    <g:else>
                        Your answer is wrong :-(
                    </g:else>
                </div>
            </div>
        </g:if>
        <g:if test="${question != null}">
            <p>
                <g:form controller="socrative" action="nextQuestion">
                    <bigtitle>${question.question}</bigtitle><p>
                    <box>
                        <input type="checkbox" name="answer1correct">
                        ${question.answer1.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer2correct"></td>
                        ${question.answer2.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer3correct"></td>
                        ${question.answer3.answer}<br>
                    </box>
                    <box>
                        <td><input type="checkbox" name="answer4correct"></td>
                        ${question.answer4.answer}<br>
                    </box>
                    <input name="index" value="${index}" type="hidden"> <br>
                    <input name="questionblockID" value="${questionblockID}" type="hidden"> <br>
                    <button type="submit" name="continue" >next</button>
                </g:form>
            </p>
        </g:if>
        <g:else>
            Fertig!
        </g:else>
    </body>
    <script>
        // Get the modal
        var modal = document.getElementById('myModal');

        // Get the button that opens the modal
        var btn = document.getElementById("openModalButton");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal
        btn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };

        window.onload = function() {
          var button = document.getElementById('openModalButton')  ;
          button.hidden = true;
          button.click();
        };
    </script>
</html>

