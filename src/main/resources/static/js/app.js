let person = document.getElementById('person');
let image = document.getElementById('img-main');
let heart = document.querySelector('.fa-heart');

let likedIds = [];
if (localStorage.getItem('likedIds') === null) {
  likedIds = [];
} else if (Array.isArray(localStorage.getItem('likedIds'))) {
  console.log("IN ELSE IFFFF")
  likedIds = JSON.parse(localStorage.getItem('likedIds'));
} else {
  console.log(localStorage.getItem('likedIds'));
  likedIds = JSON.parse(localStorage.getItem('likedIds'));
}

let showLikedOnly = false;
let likedIterator = 0;

console.log('i1: ' + i);
console.log("startid: " + "[[${startid}]]");

document.getElementById('next-btn').addEventListener('click', nextPerson);
document.onkeydown = checkKey;

function checkKey(e) {

  e = e || window.event;
  if (e.keyCode == '37') {
    previousPerson();
  } else if (e.keyCode == '39') {
    nextPerson();
  }

}
document.getElementById('next-btn').addEventListener('click', checkHeart);
let showLiked = document.querySelector(".show-liked")
showLiked.addEventListener('click', () => {
  if (!showLikedOnly) {
    showLikedOnly = true;
    showLiked.textContent = 'Show All';
  } else {
    showLikedOnly = false;
    showLiked.textContent = 'Show Liked';
  }
  nextPerson();
})

document.getElementById('previous-btn').addEventListener('click', previousPerson);
document.getElementById('previous-btn').addEventListener('click', checkHeart);
formDeleteOne = document.getElementById("delete-one-form");
console.log("FORM");
console.log(formDeleteOne.action);

let deleteOneInput = document.querySelector('.delete-one');
formDeleteOne.addEventListener('click', () => {
  formDeleteOne.action = `/deleteperson/${peopleArr[i].id}`;
  deleteOneInput.value = i;
  console.log(deleteOneInput.value);
  console.log("ACTION");
  console.log(formDeleteOne.action);
  console.log("In deleteOne");
});
heart.addEventListener('click', changeHeart);
console.log('heart');
console.log(heart);


console.log('[[${people}]]')

window.addEventListener('load', (event) => {
  console.log('page is fully loaded');
  console.log(peopleArr);
  // i = '[[${startid}]]';
  console.log("i: " + i);
});

// /*[# th:each="n : ${people}"]*/
// peopleArr.push(new Person("[(${n.firstName})]", "[(${n.lastName})]", "[(${n.pictureUrl})]", "[(${n.lookingFor})]",
//   "[(${n.id})]"));
// console.log("ADDING PERSON");
// /*[/]*/

function previousPerson() {
  displayPerson(false);
  console.log("previous person")
}

function nextPerson() {
  console.log("next person");
  displayPerson(true);
}

function displayPerson(next) {

  if (!showLikedOnly) {
    heart.style.display = "inherit"
    console.log("IN DISPLAY:::::::::::::::: " + next)
    if (next === false) {
      i--;
    } else {
      i++;
    }
    console.log(i);
    console.log("DUPA");
    console.log(image.src);

    if (i < 0) {
      heart.style.display = "none";
      i = -1;
      console.log(i);
      person.innerHTML = "You have reached the beginning";
      document.getElementById('img-main').src = 'https://source.unsplash.com/400x400/?random';

    } else if (i >= peopleArr.length) {
      heart.style.display = "none";
      document.getElementById('img-main').src = 'https://source.unsplash.com/400x400/?random';
      person.innerHTML = "You have reached the end";
      i = peopleArr.length;
      console.log("END");
    } else {
      document.getElementById('img-main').src = peopleArr[i].pictureUrl;
      console.log("ELSE");
      person.innerHTML = `
				First Name: ${peopleArr[i].firstName} <br> 
				Last Name: ${peopleArr[i].lastName} <br>
				Looking for: ${peopleArr[i].lookingFor} <br>
				ID: ${peopleArr[i].id} <br>
				`;
    }
    return i;
  } else {
    console.log("IN LIKED PEOPLE, iterator: " + likedIds);

    let likedPerson = null;

    console.log("peopleArr.length: " + peopleArr.length)
    // for (let i = 0; i < )

    for (let person of peopleArr) {
      console.log("IN LOOP");
      if (person.id === likedIds[likedIterator]) {
        likedPerson = person;
        break;
      }
    }

    heart.style.display = "none"
    console.log("IN DISPLAY:::::::::::::::: " + next)
    if (next === false) {
      likedIterator--;
    } else {
      likedIterator++;
    }

    if (likedIterator < 0) {
      heart.style.display = "none";
      likedIterator = -1;
      console.log(likedIterator);
      person.innerHTML = "You have reached the beginning";
      document.getElementById('img-main').src = 'https://source.unsplash.com/400x400/?random';

    } else if (likedIterator > likedIds.length) {
      heart.style.display = "none";
      document.getElementById('img-main').src = 'https://source.unsplash.com/400x400/?random';
      person.innerHTML = "You have reached the end";
      likedIterator = likedIds.length;
      console.log("END");
    } else {
      if (likedPerson === null) {
        console.log("UNDEFINED");
        if (next) nextPerson();
        else previousPerson();
      } else {

        document.getElementById('img-main').src = likedPerson.pictureUrl;
        console.log("ELSE");
        person.innerHTML = `
							First Name: ${likedPerson.firstName} <br> 
							Last Name: ${likedPerson.lastName} <br>
							Looking for: ${likedPerson.lookingFor} <br>
							ID: ${likedPerson.id} <br>
							`;
      }
    }

  }

}

function changeHeart(e) {
  if (i >= 0) {
    console.log(i);
    let id = peopleArr[i].id;
    console.log(id);
    if (localStorage.getItem('likedIds') === null) {
      likedIds = [];
    } else if (Array.isArray(localStorage.getItem('likedIds'))) {
      console.log("IN ELSE IFFFF")
      likedIds = JSON.parse(localStorage.getItem('likedIds'));
    } else {
      console.log(localStorage.getItem('likedIds'));
      likedIds = JSON.parse(localStorage.getItem('likedIds'));
    }
    console.log(likedIds);

    if (heart.classList.contains('far')) { //far - empty
      heart.classList.remove('far');
      heart.classList.add('fas');
      likedIds.push(id);
    } else {
      heart.classList.remove('fas');
      heart.classList.add('far');
      for (let j = likedIds.length - 1; j >= 0; j--) {
        if (likedIds[j] === id) {
          console.log(likedIds);
          likedIds.splice(j, 1);
        }
      }
    }
    localStorage.setItem('likedIds', JSON.stringify(likedIds));
  }
}

function checkHeart() {
  if (i >= 0 && i < peopleArr.length) {
    if (localStorage.getItem('likedIds') !== null) {
      if (localStorage.getItem('likedIds').includes(peopleArr[i].id)) {
        if (heart.classList.contains('far')) { //far - empty
          heart.classList.remove('far');
          heart.classList.add('fas');
        }
      } else {
        if (heart.classList.contains('fas')) { //far - empty
          heart.classList.remove('fas');
          heart.classList.add('far');
        }
      }
    }
  }
}
console.log(peopleArr);