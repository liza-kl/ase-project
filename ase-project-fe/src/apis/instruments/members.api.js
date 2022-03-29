import axios from "axios";
import {useCallback, useEffect, useState} from "react";

export function createMember(urlToPost, memberData) {
    axios.post(`http://localhost:9000/${urlToPost}`, {
        firstName: memberData.firstName,
        lastName: memberData.lastName,
        memberStatus: memberData.memberStatus
    })
        .then(function (response) {
            console.log(`You've successfully created the ${memberData.memberStatus.toLowerCase()} member
             ${memberData.firstName} ${memberData.lastName}`)
        })
        .catch(function (error) {
            console.log(error);
        });
}


