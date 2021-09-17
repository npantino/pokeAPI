function getStuff() {
    let pokemonName = document.getElementById("searchBox").value;
    let id = ""
    let location_details = ""
    let originalUrl = 'https://pokeapi.co/api/v2/pokemon/{id or name}/';

    // Collect promises so we can wait to show the info box until all promises have resolved
    const pokemonPromise = fetch(`http://localhost:8080/api/http-test/pokemon/${pokemonName}`, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json()
                .then(json => {
                    console.log(json);
                    id = json.id;
                    document.getElementById("id").innerHTML = `id: ${id}`;
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


    const encounterPromise = fetch(`https://pokeapi.co/api/v2/pokemon/${pokemonName}/encounters`, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json()
                .then(json => {
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
