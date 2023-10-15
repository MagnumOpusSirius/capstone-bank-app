//function to get the users role from the JWT token in local storage:
export const getUserRole = () => {
  const token = localStorage.getItem("jwtToken");

  if (token) {
    //decode the token to get the payload
    const base64Url = token.split(".")[1];
    const base64 = base64Url.replace("/-/g", "+").replace("/_/g", "/");
    const jsonPayload = decodeURIComponent(atob(base64));

    //parse the payload to get the user role:
    const payload = JSON.parse(jsonPayload);
    return payload.role;
  }
  return null;
};
