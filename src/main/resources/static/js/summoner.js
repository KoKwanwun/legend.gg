const config = {"Content-Type" : `application/json` };

async function searchSummoner() {
    let summonerName = document.getElementById('summonerName').value;

    try {
        const response = await axios.get('/api/lol/summoner/' + summonerName);
        console.log(response)
    } catch (e) {
    }
}