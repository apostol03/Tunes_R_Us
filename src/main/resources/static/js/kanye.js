fetch('https://api.kanye.rest/')
    .then(data => data.json())
    .then(info => {
        document.getElementById('kanye').textContent = '"' + info.quote + '..."';
    })