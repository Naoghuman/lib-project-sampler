/**
 * Copyright (C) 2018 - 2018 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.project.sampler.internal.converter;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ProjectNumberConverter {
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     */
    public static final int FIVE_LEADING_ZEROS = 5;
    
    private static final int DEFAULT_LEADING_ZEROS = 2;
    
    /**
     * Converts the given {@code projectNr} to a {@link java.lang.String} with 
     * following format:
     * <ul>
     * <li>{@code leadingZeros} fills {@code projectNr} with leading zeros. 
     *     For example '3, 1' == '001', '3, 12' == '012', '3, 123' == '123'.</li>
     * </ul>
     * 
     * If {@code projectNr} is lesser then {@code 0 (zero)} then internal it will 
     * converted to zero.
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  leadingZeros how many leading zeros should be added?
     * @param  projectNr the nummer from the project.
     * @return the formated String.
     */
    public static String convert(final int leadingZeros, final int projectNr) {
        final StringBuilder sb = new StringBuilder();
        sb.append(""); // NOI18N
        
        final int number = projectNr < 0 ? 0 : projectNr;
        sb.append(String.format("%0" + leadingZeros + "d", number)); // NOI18N
        
        return sb.toString();
    }
    
    /**
     * 
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  projectOrSampleNr
     * @return 
     */
    public static String convert(int projectOrSampleNr) {
        return convert(DEFAULT_LEADING_ZEROS, projectOrSampleNr);
    }
    
}
