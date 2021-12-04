let divWithAlbums = document.getElementById('no-albums');

function noAlbums() {
    if (divWithAlbums.children.length === 0) {
        let noResult = document.createElement('h1');
        let text = document.createTextNode('No matches found...');

        noResult.appendChild(text);
        noResult.style.color = 'white';
        noResult.style.margin = 'auto';
        noResult.style.textAlign = 'center';
        noResult.style.padding = '80px';
        noResult.style.width = '100%';

        divWithAlbums.appendChild(noResult);
    }
}
noAlbums();