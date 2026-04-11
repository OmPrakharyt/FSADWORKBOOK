import { useState,useEffect } from "react";

function UserList(){

const [users,setUsers] = useState([])
const [loading,setLoading] = useState(true)
const [error,setError] = useState(null)

useEffect(()=>{

fetch("http://localhost:8080/api/users")

.then(res=>res.json())

.then(data=>{
setUsers(data)
setLoading(false)
})

.catch(()=>{
setError("Failed to fetch users")
setLoading(false)
})

},[])

if(loading) return <p>Loading...</p>
if(error) return <p>{error}</p>

return(

<div className="container">

<h2>Users From Backend API</h2>

<table>

<thead>
<tr>
<th>Name</th>
<th>Email</th>
<th>Phone</th>
</tr>
</thead>

<tbody>

{users.map(user=>(
<tr key={user.id}>
<td>{user.name}</td>
<td>{user.email}</td>
<td>{user.phone}</td>
</tr>
))}

</tbody>

</table>

</div>

)

}

export default UserList