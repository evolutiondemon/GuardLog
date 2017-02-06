<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="bootstrap.css"/>
    <g:layoutHead/>
    <style>
    body {
        min-height: 75rem;
        padding-top: 8rem;
        background-color:#3b3b3b;
        background: image(MNIS_800x800_o8.png);
    }
    </style>
</head>

<body>

<g:render template="/templates/navbar"/>
<asset:javascript src="application.js"/>
<g:layoutBody/>
</body>
</html>
