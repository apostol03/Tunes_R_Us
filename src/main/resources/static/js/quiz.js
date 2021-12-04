function buildQuiz() {
    const output = [];

    shuffleArray(pickCorrectQuestions()).forEach(
        (currentQuestion, questionNumber) => {
            const answers = [];
            for (let letter in currentQuestion.answers) {
                answers.push(
                    `<div class="radio-div">
                        <label class="radio-btn">
                            <input type="radio" name="question${questionNumber}" value="${letter}">
                                ${currentQuestion.answers[letter]} <br>
                        </label>
                    </div>`
                );
            }

            output.push(
                `<div class="question"> <h3>${currentQuestion.question}</h3> </div>
                <div class="answers"> ${answers.join('')} </div>`
            );
        }
    );

    quizContainer.innerHTML = output.join('');
}

function showResults() {
    const answerContainers = quizContainer.querySelectorAll('.answers');

    let numCorrect = 0;
    let points = 0;

    pickCorrectQuestions().forEach((currentQuestion, questionNumber) => {
        const answerContainer = answerContainers[questionNumber];
        const selector = `input[name=question${questionNumber}]:checked`;
        const userAnswer = (answerContainer.querySelector(selector) || {}).value;

        if (userAnswer === currentQuestion.correctAnswer) {
            numCorrect++;
            points += currentQuestion.points;
            answerContainers[questionNumber].style.color = 'lightgreen';
        } else {
            answerContainers[questionNumber].style.color = 'red';
        }
    });

    resultsContainer.innerHTML = `${numCorrect} correct answers out of 
                        ${pickCorrectQuestions().length} | Points earned: ${points}`;
    buttonDiv.innerHTML = `<button onclick="location.reload()" id="try-again">Try Again</button>
                           <button onclick="location.href = '/quiz'" id="back">
                               Back to Quizzes
                           </button>`;
    return points;
}

function shuffleArray(array) {
    let currentIndex = array.length, randomIndex;

    while (currentIndex !== 0) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex--;

        [array[currentIndex], array[randomIndex]] = [
            array[randomIndex], array[currentIndex]];
    }
    return array;
}

function pickCorrectQuestions() {
    let url = window.location.href;
    switch (url) {
        case "http://localhost:8080/quiz/1":
            return hipHopQuestions;
        case "http://localhost:8080/quiz/2":
            return rbQuestions;
        case "http://localhost:8080/quiz/3":
            return metalQuestions;
        case "http://localhost:8080/quiz/4":
            return edmQuestions;
        case "http://localhost:8080/quiz/5":
            return rockQuestions;
        case "http://localhost:8080/quiz/6":
            return jazzQuestions;
    }
}

const quizContainer = document.getElementById('quiz');
const resultsContainer = document.getElementById('results');
const submitButton = document.getElementById('submit');
const buttonDiv = document.getElementById('button-div');
const hipHopQuestions = [
    {
        question: "When did Hip Hop first emerge?",
        answers: {
            a: "1960s",
            b: "1970s",
            c: "1980s"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Who was the first rapper?",
        answers: {
            a: "Coke La Rock",
            b: "Jay-Z",
            c: "Ice Cube"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which rap group was Ice Cube a member of?",
        answers: {
            a: "Outkast",
            b: "Black Eyed Peas",
            c: "N.W.A"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "How many albums does Kanye West have?",
        answers: {
            a: "10",
            b: "12",
            c: "9"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Where was Dr. Dre born?",
        answers: {
            a: "Compton",
            b: "New Jersey",
            c: "Malibu"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Who founded the company Beats?",
        answers: {
            a: "Diddy",
            b: "Jay-Z",
            c: "Dr. Dre"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which artist is from New York?",
        answers: {
            a: "Nina Simone",
            b: "Snoop Dogg",
            c: "A$AP Rocky"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "What was the name of Nas’ debut album in 1994?",
        answers: {
            a: "Illmatic",
            b: "The Dynasty",
            c: "I Am..."
        },
        correctAnswer: "a",
        points: 5
    }
];
const rbQuestions = [
    {
        question: "What does R&B stand for?",
        answers: {
            a: "Rock and blues",
            b: "Rhythm and blues",
            c: "Rock and bass"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "When was the term R&B music first introduced?",
        answers: {
            a: "Around the 1940s",
            b: "Around the 1950s",
            c: "Around the 1960s"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "What is the first album of Destiny’s Child?",
        answers: {
            a: "Destiny’s Child",
            b: "8 Days of Christmas",
            c: "The Writing’s on the Wall"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which musical instrument does Alicia Keys often play?",
        answers: {
            a: "Electric guitar",
            b: "Piano",
            c: "Violin"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which of the following was a hit song in 1990 by Whitney Houston?",
        answers: {
            a: "I Have Nothing",
            b: "I’m Your Baby Tonight",
            c: "Miracle"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which famous singer added more electronic components to his song to create" +
            " smoother dancefloor-friendly sounds, which are typical of contemporary R&B?",
        answers: {
            a: "Michael Jackson",
            b: "Justin Timberlake",
            c: "Robert Sylvester Kelly"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "In the “We Are One” concert in 2009, which singer performed the song " +
            "“Higher Ground” with Shakira and Usher?",
        answers: {
            a: "Adam Levine",
            b: "Stevie Wonder",
            c: "Blake Shelton"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which singer or band won the most Grammy Awards for Best R&B Album?",
        answers: {
            a: "TLC",
            b: "Alicia Keys",
            c: "John Legend"
        },
        correctAnswer: "b",
        points: 5
    },
];
const metalQuestions = [
    {
        question: "Paul Di'Anno was the lead singer of which band?",
        answers: {
            a: "Dream Theater",
            b: "Metallica",
            c: "Iron Maiden"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "After leaving Metallica, Dave Mustaine became lead vocalist for:",
        answers: {
            a: "Megadeth",
            b: "He never left Metallica",
            c: "AC/DC"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which one of these is/was the longest-serving band?",
        answers: {
            a: "Rolling Stones",
            b: "Guns 'n' Roses",
            c: "Metallica"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which band released the album 'Nightmare'?",
        answers: {
            a: "Black Sabbath",
            b: "Iron Maiden",
            c: "Avenged Sevenfold"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which Metallica album sold the most copies?",
        answers: {
            a: "Kill 'em All",
            b: "Ride the Lightning",
            c: "Metallica"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which one of these bands performs whilst wearing masks?",
        answers: {
            a: "Korn",
            b: "Lamb of God",
            c: "Slipknot"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which one of these songs are not sung by 'System of a Down'?",
        answers: {
            a: "Toxicity",
            b: "Aliens",
            c: "Chop Suey!"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which one of these bands is not American?",
        answers: {
            a: "Cradle of Filth",
            b: "Slayer",
            c: "Metallica"
        },
        correctAnswer: "a",
        points: 5
    }
];
const edmQuestions = [
    {
        question: "What's the stage name of Swedish DJ Tim Bergling?",
        answers: {
            a: "Deadmau5",
            b: "Avicii",
            c: "Akon"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "The origins of trance are usually traced to which country?",
        answers: {
            a: "United States",
            b: "Germany",
            c: "Japan"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "In which city did the EDM festival Electric Daisy Carnival first appear?",
        answers: {
            a: "San Diego",
            b: "Las Vegas",
            c: "Los Angeles"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which EDM label was co-founded by DJ Armin van Buuren?",
        answers: {
            a: "Armada Music",
            b: "Jive Electro",
            c: "Astralwerks"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which DJ is known for throwing a cake at an audience member at every show?",
        answers: {
            a: "Calvin Harris",
            b: "David Guetta",
            c: "Steve Aoki"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Calvin Harris teamed up with pop band Haim to create which smash hit?",
        answers: {
            a: "\"Pray to God\"",
            c: "\"Summer\"",
            b: "\"How Deep is Your Love\""
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which recreational drug is often associated with EDM?",
        answers: {
            a: "MDMA",
            b: "Heroin",
            c: "Marijuana"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "How much was the EDM industry worth in 2014?",
        answers: {
            a: "$6.2 billion",
            b: "$2.3 billion",
            c: "$988 million"
        },
        correctAnswer: "a",
        points: 5
    }
];
const rockQuestions = [
    {
        question: "The Led Zeppelin hit 'Stairway to Heaven' was " +
            "played live for the very first time in which city?",
        answers: {
            a: "Belfast",
            b: "Dublin",
            c: "Berlin"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "The album 'On An Island' was released by which Pink Floyd member?",
        answers: {
            a: "Roger Waters",
            b: "Richard Wright",
            c: "David Gilmour"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which Guns 'n' Roses guitarist performs the guitar solo on " +
            "'Sweet Child O' Mine'?",
        answers: {
            a: "Axl Rose",
            b: "Slash",
            c: "Dizzy Reed"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which androgynous musician is known for his album - 'Purple Rain'?",
        answers: {
            a: "Prince",
            b: "Eric Clapton",
            c: "Alice Cooper"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Keith Richards collaborated with Mick Jagger and" +
            " played the guitar for which first top-ten hit?",
        answers: {
            a: "Carry on Wayward Son",
            b: "Born to Be Wild",
            c: "The Last Time"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Name the guitar slinging musician who was referred to as 'God' by band members.",
        answers: {
            a: "Elton John",
            b: "Eric Clapton",
            c: "Prince"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "When did rock and roll music originate?",
        answers: {
            a: "1930s",
            b: "1940s",
            c: "1950s"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Who is known as the ‘King of Rock 'n' Roll’?",
        answers: {
            a: "Elvis Presley",
            b: "Prince",
            c: "Johnny Cash"
        },
        correctAnswer: "a",
        points: 5
    },
];
const jazzQuestions = [
    {
        question: "From which country did Jazz music and dance originate?",
        answers: {
            a: "France",
            b: "The United States",
            c: "Spain"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "What are syncopations, a typical feature of jazz?",
        answers: {
            a: "Off-beat music",
            b: "A brass instrument",
            c: "A slow mournful song"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which of the following people is a great jazz singer and trumpeter?",
        answers: {
            a: "Bob Marley",
            b: "Elton John",
            c: "Louis Armstrong"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "The first ever jazz recording was performed by which band?",
        answers: {
            a: "The Original Dixieland Jazz Band",
            b: "The Jazz Messengers",
            c: "Modern Jazz Quartet"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "The famous clarinettist Benny Goodman is often associated " +
            "with which form of jazz?",
        answers: {
            a: "Afro-Latin jazz",
            b: "Swing jazz",
            c: "Bop jazz"
        },
        correctAnswer: "b",
        points: 5
    },
    {
        question: "Which of the following is a jazz song?",
        answers: {
            a: "Stand By Me by Ben E. King",
            b: "Yesterday by the Beatles",
            c: "Fly Me To The Moon by Frank Sinatra"
        },
        correctAnswer: "c",
        points: 5
    },
    {
        question: "Which musical instrument is typically known to “impersonate” jazz?",
        answers: {
            a: "Saxophones",
            b: "Trombones",
            c: "Trumpets"
        },
        correctAnswer: "a",
        points: 5
    },
    {
        question: "Which of the following is not the name of a jazz group?",
        answers: {
            a: "Louis Armstrong and his Hot Seven",
            b: "Louis Armstrong and his Jazz Buddies",
            c: "Louis Armstrong and his All Stars"
        },
        correctAnswer: "b",
        points: 5
    },
];

buildQuiz();

submitButton.addEventListener('click', showResults);
