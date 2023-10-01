    // Get the modal
    //var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    //window.onclick = function(event) {
    //  if (event.target == modal) {
    //    modal.style.display = "none";
    //  }
    //}

    // create a JavaScript cookie to be able to store the user’s Id for subsequent requests once they’re logged in
    //JavaScript can create, read, and delete cookies with the document.cookie property.
    console.log('connected');
    const cookieArr = document.cookie.split("=");
    const userId = cookieArr[1];

   /********end cookie*****/

    const LostPetForm = document.getElementById("lostPet-form");
    const lostPetContainer = document.getElementById("lostPet-container");



    //create a function to logout user
    function handleLogout(){
        let c = document.cookie.split(";");
        for(let i in c){
            document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
        }
    }



    //create a function to handle a post request from lostPet form
    const petType = document.getElementById('pet-type');
    const petName = document.getElementById('pet-name');
    const gender = document.getElementById('pet-gender');
    const lastSeen = document.getElementById('pet-lastSeen');
    const contactEmail = document.getElementById('email');
    const date = document.getElementById('date');

    const handleLostPetForm = async (e)=>{
          e.preventDefault();
          let bodyObj ={
          petType: petType.value,
          petName: petName.value,
          gender: gender.value,
          lastSeen: lastSeen.value,
          contactEmail: contactEmail.value,
          date: date.value
       }

          await addLostPet(bodyObj); //invoke function addLostPet

          //empty input after clicking submit
           petType.value = ''
           petName.value = ''
           gender.value = ''
           lastSeen.value = ''
           contactEmail.value = ''
           date.value = ''

      }
      //create addLostPet function to make a post request from user

      const baseUrl = 'http://localhost:8080/api/v1/lostPets/'
      const headers={
              'Content-Type': 'application/json'
          }

       async function addLostPet(obj){
       const response = await fetch(`${baseUrl}user/${userId}`,{
            method: "POST",
            body: JSON.stringify(obj), //Convert a JavaScript object into a string with JSON.stringify().
            headers: headers
      })
           .catch(err => console.error(err.message))
           if(response.status === 200){
              return getAllLostPetByUser(userId);

           }
      }
   // invoke handleLostPetForm function when user click submit button on form
   LostPetForm.addEventListener('submit', handleLostPetForm);

    //create a function to retrieve all lost pets by userId
    async function getAllLostPetByUser(userId){
        await fetch(`${baseUrl}user/${userId}`,{
        method: "GET",
        headers: headers
        })
        .then(response => response.json())
        .then(data => createPetCards(data))
        .catch(err => console.error(err))
    }

    //create a function createPetCards to display lost pets to user
    const createPetCards = (array) =>{
     lostPetContainer.innerHTML = '';
     array.forEach(obj =>{
        let petCard = document.createElement('div');
        petCard.classList.add("m-2");

        petCard.innerHTML =`

            <div class="card">
            <div class="card-content">
            <h4>Your lost Pet</h4>
            <p>Pet Type: ${obj.petType}</p>
            <p>Pet Name: ${obj.petName}</p>
            <p>Gender: ${obj.gender}</p>
            <p>Last Seen: ${obj.lastSeen}</p>
            <p>Contact: ${obj.contactEmail}</p>
            <p>Date: ${obj.date}</p>
             </div>
             <div class="card-footer">
            <button class="btn-card" onclick="handleDeleteLostPet(${obj.id})">Delete</button>
            <button class="btn-card" onclick="getLostPetById(${obj.id})" type="button" data-bs-toggle="modal" data-bs-target="#pet-update-modal">Edit</button>
            </div>
            </div>
         `
        lostPetContainer.append(petCard);
     })
  }
     //create a function to send a get request to lostPetById endpoint and invoke populateModal function to  update lostPet info when clicking update button
       async function getLostPetById(lostPetId){
             await fetch(`${baseUrl}+${lostPetId}`,{
             method:"GET",
             headers:headers
             })
             .then(res => res.json())
             .then(data => populateModal(data))
             .catch(err => console.error(err.message))
       }

       //create populateModal function accepts an objects an argument and uses that object to populate the field within modal
       const updateLostPetBtn = document.getElementById('update-lostPet-btn');
       const modalType = document.getElementById('modal-type');
       const modalName = document.getElementById('modal-name');
       const modalGender = document.getElementById('modal-gender');
       const modalLastSeen = document.getElementById('modal-lastSeen');
       const modalEmail = document.getElementById('modal-email');
       const modalDate = document.getElementById('modal-date');

       const populateModal = (obj) =>{
             modalType.value = ''
             modalName.value = ''
             modalGender.value = ''
             modalLastSeen.value = ''
             modalEmail.value = ''
             modalDate.value= ''

             modalType.value = obj.petType
             modalName.value = obj.petName
             modalGender.value = obj.gender
             modalLastSeen.value = obj.lastSeen
             modalEmail.value = obj.contactEmail
             modalDate.value = obj.date

             updateLostPetBtn.setAttribute('data-lostPet-id',obj.id);
        }

        //create a function that handle update pet info
        async function handleLostPetUpdate(lostPetId){
         let bodyObj = {
            id: lostPetId,
            petType: modalType.value,
            petName: modalName.value,
            gender: modalGender.value,
            lastSeen: modalLastSeen.value,
            contactEmail: modalEmail.value,
            date: modalDate.value
         }
         await fetch(baseUrl+lostPetId,{
                method: "PUT",
                body: JSON.stringify(bodyObj),
                headers: headers
         })
            .catch(err => console.error(err))
            return getAllLostPetByUser(userId);
        }
        // add click EventListener to updateLostPetBtn variable
              updateLostPetBtn.addEventListener('click', (e)=>{
              let lostPetId = e.target.getAttribute('data-lostPet-id')
              handleLostPetUpdate(lostPetId);
             })
        //create a function that handle delete lost pet by Id
        async function handleDeleteLostPet(lostPetId){
            await fetch(baseUrl+lostPetId,{
                  method: "DELETE",
                  headers: headers
            })
            .catch(err => console.error(err))
            return getAllLostPetByUser(userId);
        }


        // invoke getAllLostPetByUser function
        getAllLostPetByUser(userId);

/*******************************************************************************************************************/
      //FOUND PET FEATURES

      //create a function to handle a post request from lostPet form
        const foundPetType = document.getElementById('found-petType');
        const foundPetGender = document.getElementById('found-petGender');
        const foundLocation = document.getElementById('found-location');
        const foundPetEmail = document.getElementById('found-petEmail');
        const foundPetDate = document.getElementById('found-petDate');
        const foundPetForm = document.getElementById('foundPet-form');
        const foundPerContainer = document.getElementById('foundPet-container');

        const handleFoundPetForm = async (e) =>{
              e.preventDefault();
              let bodyObj ={
                petType: foundPetType.value,
                gender: foundPetGender.value,
                found: foundLocation.value,
                contactEmail: foundPetEmail.value,
                date: foundPetDate.value
            }
              await addFoundPet(bodyObj); //invoke addFoundPet function
              //empty input after clicking submit
              foundPetType.value = ''
              foundPetGender.value = ''
              foundLocation.value = ''
              foundPetEmail.value = ''
              foundPetDate.value = ''

            }
            //create addFoundPet function to make a post request from user
            const baseUrlFoundPet = 'http://localhost:8080/api/v1/foundPets/'

            async function addFoundPet(obj){
            const response = await fetch(`${baseUrlFoundPet}user/${userId}`,{
                  method: "POST",
                  body: JSON.stringify(obj),
                  headers: headers
             })
                .catch(err => console.error(err.message))
                if(response.status === 200){
                  return getAllFoundPetByUser(userId);
                }
            }


           // invoke handleFoundPetForm function when user click submit button on form
             foundPetForm.addEventListener('submit', handleFoundPetForm);

              //create a function to retrieve all found pets by userId
              async function getAllFoundPetByUser(userId){
                  await fetch(`${baseUrlFoundPet}user/${userId}`,{
                  method: "GET",
                  headers: headers
                  })
                  .then(response => response.json())
                  .then(data => createFoundPetCards(data))
                  .catch(err => console.error(err))
              }

              //create a function createPetCards to display lost pets to user
              const createFoundPetCards = (array) =>{
               foundPerContainer.innerHTML = '';
               array.forEach(obj =>{
                  let petCard = document.createElement('div');
                  petCard.classList.add("m-2");

                  petCard.innerHTML =`

                      <div class="card">
                      <div class="card-content">
                      <h4>Your Found Pet</h4>
                      <p>Pet Type: ${obj.petType}</p>
                      <p>Gender: ${obj.gender}</p>
                      <p>Found: ${obj.found}</p>
                      <p>Contact: ${obj.contactEmail}</p>
                      <p>Date: ${obj.date}</p>
                       </div>
                       <div class="card-footer">
                      <button class="btn-card" onclick="handleDeleteFoundPet(${obj.id})">Delete</button>
                      <button class="btn-card" onclick="getFoundPetById(${obj.id})" type="button" data-bs-toggle="modal" data-bs-target="#foundPet-update-modal">Edit</button>
                      </div>
                      </div>
                   `
                  foundPerContainer.append(petCard);
               })
            }
         // create a function to send a get request to foundPetById endpoint and invoke populateModal function to update found pet info when click edit button
               async function getFoundPetById(foundPetId){
                      await fetch(`${baseUrlFoundPet}+${foundPetId}`,{
                         method: "GET",
                         headers: headers
                      })
                      .then(res => res.json())
                      .then(data => populateFoundPetModal(data))
                      .catch(err => console.error(err.message))
               }

           //create populateModal function for getFoundPetById function, accepts an object as argument and uses that object to populate the field within modal
                const modalFoundType = document.getElementById('modal-foundType');
                const modalFoundGender = document.getElementById('modal-foundGender');
                const modalFound = document.getElementById('modal-found');
                const modalFoundEmail = document.getElementById('modal-foundEmail');
                const modalFoundDate = document.getElementById('modal-foundDate');
                const updateFoundPetBtn = document.getElementById('update-foundPet-btn');

                const populateFoundPetModal = obj =>{
                      modalFoundType.value = ''
                      modalFoundGender.value = ''
                      modalFound.value = ''
                      modalFoundEmail.value = ''
                      modalFoundDate.value = ''

                      modalFoundType.value = obj.petType
                      modalFoundGender.value = obj.gender
                      modalFound.value = obj.found
                      modalFoundEmail.value = obj.contactEmail
                      modalFoundDate.value = obj.date

                      updateFoundPetBtn.setAttribute('data-foundPet-id',obj.id);
                }


              //create a function that handle update found pet info
                  async function handleFoundPetUpdate(foundPetId){
                        let bodyObj = {
                        id: foundPetId,
                        petType: modalFoundType.value,
                        gender: modalFoundGender.value,
                        found: modalFound.value,
                        contactEmail: modalFoundEmail.value,
                        date: modalFoundDate.value
                        }
                  await fetch(baseUrlFoundPet+foundPetId,{
                        method: "PUT",
                        body: JSON.stringify(bodyObj),
                        headers: headers
                  })
                    .catch(err => console.error(err))
                    return getAllFoundPetByUser(userId);
                 }
                 //add click EventListener to updateFoundPetBtn variable
                  updateFoundPetBtn.addEventListener('click', (e)=>{
                  let foundPetId = e.target.getAttribute('data-foundPet-id');
                       handleFoundPetUpdate(foundPetId);
                 })

                 //create a function that handle delete found pet by id
                 async function handleDeleteFoundPet(foundPetId){
                       await fetch(baseUrlFoundPet+foundPetId,{
                             method: "DELETE",
                             headers: headers
                       })
                       .catch(err => console.error(err))
                       return getAllFoundPetByUser(userId);
                 }

                 //invoke getAllFoundPetByUser function
                 getAllFoundPetByUser(userId);

/*Cookies are data, stored in small text files, on your computer.
When a web server has sent a web page to a browser, the connection is shut down,
and the server forgets everything about the user.
Cookies were invented to solve the problem "how to remember information about the user":
When a user visits a web page, his/her name can be stored in a cookie.
Next time the user visits the page, the cookie "remembers" his/her name.
Cookies are saved in name-value pairs*/
/*
When a browser requests a web page from a server, cookies belonging to the page are added to the request.
This way the server gets the necessary data to "remember" information about users.*/