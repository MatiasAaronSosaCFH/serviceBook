
package com.servicebook.service;

import com.servicebook.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author julip
 */

@Service
public class TrabajoService {
    
    @Autowired
    private TrabajoRepository trabajoRepository;
    
    
}
