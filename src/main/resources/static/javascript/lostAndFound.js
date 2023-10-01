console.log('connected');

 const headers={
              'Content-Type': 'application/json'
          }

// create a function that make a get request to retrieve all lost pets from database
    async function getAllLostPets(){
       await fetch('http://localhost:8080/api/v1/lostPets/allLostPets', {
              method:"GET",
              header: headers
        })
         .then(response => response.json())
         .then(data => lostPetCards(data))
         .catch(err => console.error(err))
    }


 //create lostPetCard function to display to landing page
    const showAllLostPets = document.getElementById('allLostPets')
    const lostPetCards = array =>{
          showAllLostPets.innerHTML = '';
          array.forEach(obj =>{
          let petCard = document.createElement('div');
          petCard.classList.add('m-2');
          petCard.innerHTML = `
                         <div class="card mb-3 mt-3" style="background-color: rgb(240, 240, 240)">
                         <div class="card-content row">
                         <div class="col-md-4">
                         <img class="img-thumbnail" style="width:160px"  src="./img/whereMyPet.png"/>
                         </div>
                         <div class="col-md-4">
                         <div class="card-body">
                         <p>Pet Type: <b>${obj.petType}</b></p>
                         <p>Pet Name: <b>${obj.petName}</b></p>
                         <p>Gender: <b>${obj.gender}</b></p>
                         </div>
                         </div>
                         <div class="col-md-4">
                         <div class="card-body">
                         <p>Last Seen: <b>${obj.lastSeen}</b></p>
                         <p>Contact: <b>${obj.contactEmail}</b></p>
                         <p>Date: <b>${obj.date}</b></p>
                         </div>
                         </div>
                         </div>
                         </div>

                         `
              showAllLostPets.append(petCard);
          })
    }

// invoke getAllLostPets function
    getAllLostPets();

/*********************************************************************************************/

//create a function that make a get request to retrieve all found pets from database
    async function getAllFoundPets(){
          await fetch('http://localhost:8080/api/v1/foundPets/allFoundPets',{
                 method: "GET",
                 headers: headers
          })
          .then(response => response.json())
          .then(data => foundPetCards(data))
          .catch(err => console.error(err))
    }

//create foundPetCards function to display all found pets
    const showAllFoundPets = document.getElementById('allFoundPets');
    const foundPetCards = array =>{
           showAllFoundPets.innerHTML = '';
           array.forEach(obj =>{
             let petCard = document.createElement('div');
             petCard.classList.add('m-2');
             petCard.innerHTML =`
             <div class="card mb-3 mt-3" style="background-color: rgb(240, 240, 240)">
                 <div class="card-content row" >
                 <div class="col-md-4">
                 <img class="img-thumbnail" style="width:160px" src="./img/whereMyPet.png"/>
                 </div>
                  <div class="col-md-4">
                <div class="card-body">
                <p>Pet Type: <b>${obj.petType}</b></p>
               <p>Gender: <b>${obj.gender}</b></p>
               <p>Last Seen: <b>${obj.found}</b></p>
               </div>
               </div>
               <div class="col-md-4">
               <div class="card-body">
               <p>Contact: <b>${obj.contactEmail}</b></p>
               <p>Date: <b>${obj.date}</b></p>
             </div>
             </div>
             </div>
             </div>
             `
             showAllFoundPets.append(petCard);
           })
    }
 // invoke getAllFoundPets function
        getAllFoundPets();