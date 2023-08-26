const config = {"Content-Type" : `application/json` };

const summonerName = window.location.pathname.substring(1);

axios.get('/api/lol/summoner/' + summonerName).then((Response)=>{
    let summonerNameTest = document.getElementById('summonerNameTest');
    summonerNameTest.innerText = Response.data;
}).catch((Error)=>{
    console.log(Error);
})

function searchSummoner() {
    let summonerName = document.getElementById('summonerName').value;
    location.href = "/" + summonerName;
}