/* 
 * Copyright 2015 Intel(r) Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http ://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#pragma once

#ifndef _WEAK_ENCRYPTOR_HPP
#define _WEAK_ENCRYPTOR_HPP

#include "umf/encryptor.hpp"

namespace umf
{

enum CryptAlgo
{
    DEFAULT, WEAK, NONE
};

//Some testing class for encryption
class WeakEncryptor : public umf::Encryptor
{
public:
    WeakEncryptor(char _key) : key(_key) { }

    virtual void encrypt(const umf_string &input, umf_rawbuffer &output);

    virtual void decrypt(const umf_rawbuffer &input, umf_string &output);

    virtual umf_string getHint()
    {
        return umf_string("bad encryptor for tests");
    }

    virtual ~WeakEncryptor() { }

private:
    char key;
    static const char magic;
};


std::shared_ptr<umf::Encryptor> getEncryptor(CryptAlgo algo, bool wrong = false);

}

#endif //_WEAK_ENCRYPTOR_HPP
