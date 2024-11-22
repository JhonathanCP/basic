package com.quantumtech.mediapp_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.MediaFile;
import com.quantumtech.mediapp_backend.repo.IMediaFileRepo;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.service.IMediaFileService;


@Service
public class MediaFileServiceImpl extends CRUDImpl<MediaFile, Integer> implements IMediaFileService{

    @Autowired
    private IMediaFileRepo repo;

    @Override
    protected IGenericRepo<MediaFile, Integer> getRepo() {
        return repo;
    }

}
