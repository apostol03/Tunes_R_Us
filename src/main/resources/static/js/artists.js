let divWithArtists = document.getElementById("no-artists");
function noArtists() {
    if (divWithArtists.children.length === 0) {
        let noResult = document.createElement('h1');
        let text = document.createTextNode('No matches found...');

        noResult.appendChild(text);
        noResult.style.color = 'white';
        noResult.style.margin = '68px';
        noResult.style.display = 'flex';
        noResult.style.justifyContent = 'center';
        noResult.style.alignContent = 'center';
        noResult.style.border = '3px solid white';
        noResult.style.borderRadius = '5px';
        noResult.style.padding = '10px';

        divWithArtists.appendChild(noResult);
    }
}

noArtists();
