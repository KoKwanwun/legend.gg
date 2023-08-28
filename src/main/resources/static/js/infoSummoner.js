const summonerName = window.location.pathname.substring(1);

axios.get('/api/lol/summoner/' + summonerName).then((Response)=>{
    let infoSummonerIcon = document.getElementById('infoSummonerIcon');
    let infoSummonerName = document.getElementById('infoSummonerName');
    let infoSummonerLevel = document.getElementById('infoSummonerLevel');
    let infoSummonerSoloTier = document.getElementById('infoSummonerSoloTier');
    let infoSummonerSoloWL = document.getElementById('infoSummonerSoloWL');
    let infoSummonerFreeTier = document.getElementById('infoSummonerFreeTier');
    let infoSummonerFreeWL = document.getElementById('infoSummonerFreeWL');
    infoSummonerIcon.src = "https://ddragon.leagueoflegends.com/cdn/13.9.1/img/profileicon/" + Response.data.summoner.profileIconId + ".png";
    infoSummonerName.innerText = Response.data.summoner.name;
    infoSummonerLevel.innerText = "lv." + Response.data.summoner.summonerLevel;
    infoSummonerSoloTier.innerHTML = "솔로랭크 : " + Response.data.summonerTier.soloTier + " " + Response.data.summonerTier.soloLeaguePoints + "p";
    infoSummonerSoloWL.innerHTML = Response.data.summonerTier.soloWin + "승 " + Response.data.summonerTier.soloLoss + "패";
    infoSummonerFreeTier.innerHTML = "자유랭크 : "  + Response.data.summonerTier.freeTier + " " + Response.data.summonerTier.freeLeaguePoints + "p";
    infoSummonerFreeWL.innerHTML = Response.data.summonerTier.freeWin + "승 " + Response.data.summonerTier.freeLoss + "패";

    let summonerId = Response.data.summoner.id;

    // axios.get('/api/lol/match/progress/' + summonerId).then((Response)=>{
    //     // let infoSummonerIcon = document.getElementById('infoSummonerIcon');
    //     // infoSummonerName.innerText = Response.data.summoner.name;
    //     console.log(Response.data)
    //
    // }).catch((Error)=>{
    //     console.log(Error);
    // })

}).catch((Error)=>{
    let mainHtml = document.getElementById('mainHtml');
    mainHtml.innerHTML = "<section id=\"summoner-search\">" +
        "<p>소환사를 찾을 수 없습니다.</p>" +
        "</section>";
})

function searchSummoner() {
    let summonerName = document.getElementById('summonerName').value;
    location.href = "/" + summonerName;
}