function getSearchHistory() {
    const searchHistory = fetch(`http://localhost:8080/api/http-test/searchHistory`, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json()
                .then(json => {
                    const searchBox = document.getElementById("searchBox");
                    const rect = searchBox.getBoundingClientRect();
                    const searchHistory = document.getElementById("searchHistory");

                    searchHistory.style.position = "absolute";
                    searchHistory.style.top = rect.bottom+"px";
                    searchHistory.style.left = rect.left+"px";
                    searchHistory.style.width = rect.width+"px";

                    for (let i = 0; i < json.length; i++) {
                        let ele = document.createElement("button");
                        ele.id = "historyButton" + i;
                        ele.className = "historyButton";
                        ele.style.width = rect.width+"px";
                        ele.style.display = "inline-block";
                        ele.innerHTML = json[i];
                        let div = document.createElement("div");
                        div.id = "history" + i;
                        div.appendChild(ele);
                        searchHistory.appendChild(div);
                        ele.addEventListener('mousedown', function (event) {
                            getStuff(json[i]);
                            searchBox.value = json[i];
                        }, true);
                    }
                });
        });
}

document.getElementById("searchButton").addEventListener('click', function(event) {
    let name = document.getElementById("searchBox").value.toLowerCase();
    getStuff(name);
}, true);

function getStuff(pokemonName) {
    //let pokemonName = document.getElementById("searchBox").value.toLowerCase();
    let name = pokemonName.toLowerCase();

    // Collect promises so we can wait to show the info box until all promises have resolved
    const pokemonPromise = fetch(`http://localhost:8080/api/http-test/pokemon/${name}`, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        return response.json()
            .then(json => {
                let name = json.name;
                let id = json.id;
                document.getElementById("name").innerHTML = `Name: ${name}`;
                document.getElementById("id").innerHTML = `Id: ${id}`;
                // Pokemon typing
                let type = "";
                for (let i = 0; i < json.types.length; i++) {
                    let name = json.types[i].type.name;
                    type += name;
                    if (i !== json.types.length - 1) {
                        type += ", ";
                    }
                }
                document.getElementById("type").innerHTML = "Type: " + type;

                // Base stats and total
                let total = 0;
                for (let i = 0; i < json.stats.length; i++) {
                    document.getElementById("stat_" + (i+1)).innerHTML = json.stats[i].base_stat;
                }
                document.getElementById("stat_total").innerHTML = json.totalStats;



                // Abilities (including hidden)
                let abilities = "";
                for (let i = 0; i < json.abilities.length; i++) {
                    abilities += json.abilities[i].ability.name;
                    if (json.abilities[i].is_hidden) {
                        abilities += " (hidden)";
                    }
                    if (i !== json.abilities.length) {
                        abilities += ", ";
                    }
                }
                document.getElementById("abilities").innerHTML = `Abilities: ${abilities}`;

                // Moves

                // Learnset
                /*
                let learnset = [];
                for (let i = 0; i < json.moves.length; i++) {
                    //console.log(json.moves[i].version_group_details[0].move_learn_method.name);
                    if (json.moves[i].version_group_details[0].move_learn_method.name === "level-up") {
                        learnset.push([json.moves[i].version_group_details[0].level_learned_at, json.moves[i].move.name]);
                        //console.log(json.moves[i].move.name);
                    }
                }
                learnset.sort(function(a, b) {
                    return a[0] - b[0];
                })
                console.log(learnset);

                */
            });
    });


    const encounterPromise = fetch(`http://localhost:8080/api/http-test/encounters/${pokemonName}`, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json()
                .then(json => {
                    let location_details = "";
                    for (let i = 0; i < json.length; i++) {
                        let area = json[i].location_area.name;
                        for (let j = 0; j < json[i].version_details.length; j++) {
                            let gameName = json[i].version_details[j].version.name;
                            location_details += gameName
                            if (j === json[i].version_details.length - 1) {
                                location_details += ": "
                            }
                            else {
                                location_details += ", "
                            }
                        }
                        location_details += `${area} <br>`;
                    }
                    document.getElementById('locations').innerHTML = location_details;
                });
        });

    // Once all promises have resolved, show the info box.
    Promise.all([pokemonPromise, encounterPromise]).then(() => {
        document.getElementById("gameInfo").style.display = "block";
    })
}

document.addEventListener('keydown', function(event) {
    let name = document.getElementById("searchBox").value.toLowerCase();
    if (event.code === "Enter" && !(name == null || name === "")) {
        getStuff(name);
    }
}, true);

document.getElementById("searchBox").addEventListener('focus', function(event) {
    document.getElementById("searchHistory").innerHTML = "";
    getSearchHistory();
    document.getElementById("searchHistory").style.display = "block";
}, true);

document.getElementById("searchBox").addEventListener('focusout', function(event) {
    document.getElementById("searchHistory").style.display = "none";
}, true);


function clearSearchHistory() {
    const pokemonPromise = fetch(`http://localhost:8080/api/http-test/deleteSearchHistory`, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json',
        }
    })
}
