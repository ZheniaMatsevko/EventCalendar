
$(document).ready(function () {
    $('#sportType').change(function () {
        updateTeams();
    });

    $('#team1').change(function () {
        updateTeam2Selection();
    });

    $('#team2').change(function () {
        updateTeam2Selection();
    });
});
function updateTeams() {
    var selectedSport = $('#sportType').val();

    $.ajax({
        type: "GET",
        url: "/teams/bySport",
        data: {id: selectedSport},
        success: function (teams) {

            $('#team1').empty();
            $('#team2').empty();

            $('#team1').append($('<option></option>').attr({'value': '', 'selected': 'selected', 'disabled': 'disabled'}).text('Select a Team'));
            $('#team2').append($('<option></option>').attr({'value': '', 'selected': 'selected', 'disabled': 'disabled'}).text('Select a Team'));

            teams.forEach(function (team) {
                var option = $('<option></option>').attr('value', team.id).text(team.name + ' - ' + team.sportType.name);
                $('#team1').append(option);
                $('#team2').append(option.clone());
            });
        },
        error: function () {
            console.log('Error fetching teams');
        }
    });
}

function updateTeam2Selection() {
    // Get the selected value in Team 1
    var team1Value = $('#team1').val();

    // Disable the option with the same value in Team 2
    $('#team2 option').prop('disabled', false);
    if ($('#team2').val() === team1Value) {
        $('#team2').val('');
    }
    $('#team2 option[value="' + team1Value + '"]').prop('disabled', true);
}
