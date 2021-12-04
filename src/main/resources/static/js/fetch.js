let artistsChartDiv = document.getElementById('artists-chart-content');
let albumsChartDiv = document.getElementById('albums-chart-content');
fetch("https://cors-anywhere.herokuapp.com/https://api.deezer.com/chart/0/artists")
    .then(data => data.json())
    .then(info => {
        info.data.forEach(entry => {
            artistsChartDiv.innerHTML += `
                <div class="card h-50 ">
                    <h3 class="chart-h3"><span>${entry.position}. </span>${entry.name}</h3>
                    <img class="artist-card-img" src="${entry.picture_medium}" alt="">
                </div>`
        });
    });

fetch("https://cors-anywhere.herokuapp.com/https://api.deezer.com/chart/0/tracks")
    .then(data => data.json())
    .then(info => {
        info.data.forEach(entry => {
            albumsChartDiv.innerHTML += `
            <div class="card h-50">
                    <h5 class="chart-h3 text-truncate">
                        <span>${entry.position}. </span>${entry.title} (${entry.artist.name})
                    </h5>
                    <img class="artist-card-img" src="${entry.album.cover_medium}" alt="">
            </div>`
        })
    })
