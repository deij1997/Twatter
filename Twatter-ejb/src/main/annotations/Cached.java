/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Jesse
 */
@Inherited // dat ie inheritance heeft lmao
@Target( {ElementType.METHOD, ElementType.TYPE}) // waar hij op mag worden gezet
@Retention(RetentionPolicy.RUNTIME) // wanneer die hem moet bijhouden
public @interface Cached
{
    
}
