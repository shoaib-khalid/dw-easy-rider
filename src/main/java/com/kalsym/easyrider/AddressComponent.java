/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalsym.easyrider;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Kalsym
 */
@Getter
@Setter
@ToString
class AddressComponent {
    public String long_name;
    public String short_name;
    public List<String> types; 
    
}
