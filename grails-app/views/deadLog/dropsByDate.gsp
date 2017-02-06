<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="layout" content="default"/>
    <title>
        Drops By Date
    </title>
</head>

<body>
<div class="offset-md-2">
    <div class="col-md-7">
        <div class="card">
            <div class="card-header">
                Search by Date
            </div>

            <div class="card-block">

                    <form>
                        <div class="form-group">
                            <label for="startDate">From  </label>
                            <g:datePicker class="form-control" id="startDate" name="startDate" value="${minDate}" relativeYears="[-3..0]"
                                          noSelection="['': '-Choose-']"/>
                        </div>

                        <div class="form-group">
                            <label>To            </label>
                            <g:datePicker class="form-control" id="endDate" name="endDate" value="${maxDate}" relativeYears="[-3..0]"
                                          noSelection="['': '-Choose-']"/>
                        </div>
                        <g:actionSubmit class="btn" value="Submit" action="getDrops"/>
                    </form>

            </div>
        </div>
    </div>
</div>
</body>
</html>
