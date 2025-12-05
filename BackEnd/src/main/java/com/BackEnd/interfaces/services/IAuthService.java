package com.BackEnd.interfaces.services;

import com.BackEnd.models.Authentication;
import com.BackEnd.DTO.request.AuthRequest;

public interface IAuthService {
    Authentication authenticate(AuthRequest req);
}
