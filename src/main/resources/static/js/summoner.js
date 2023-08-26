const config = {"Content-Type" : `application/json` };

function searchSummoner() {
    let summonerName = document.getElementById('summonerName').value;
    location.href = "/" + summonerName;
}